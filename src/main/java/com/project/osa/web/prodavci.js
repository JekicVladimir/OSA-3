

$(function(){
	$('#dodajArtiklDiv').hide();
	$('#izmenaPodatakaDiv').hide();
	
	var $userName =$('#userName');
	var $password =$('#password');	
	var $listaArtikala =$('#listaArtikala');
	var $naziv =$('#nazivArtikla');
	var $opis =$('#opis');
	var $cena =$('#cena');
	var $putanjaSlike =$('#putanjaSlike');
	var $idProdavaca =$('#idProdavaca');
	var $id =$('#id');
	var idProdavca = localStorage.getItem('id');
	loadArtikls();
	
	//ucitavanje liste artikala artikala
	function loadArtikls() {
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci/'+ localStorage.getItem('id'),
			success: function(prodavac) {	
				console.log(prodavac)
				$('#listaArtikala').empty();
				$('#listaArtikala').append('<tr><th>Naziv</th><th>Opis</th><th>Cena</th><th>Slika</th></tr>');
				$.each(prodavac.artikli, function(i, artikl) {
					$('#listaArtikala tr:last').after('<tr id=' + artikl.id +'><td>' + artikl.naziv + '</td><td>' + artikl.opis + '</td><td>' + artikl.cena + '</td><td>' + artikl.putanjaSlike+ '</td><td><button class="edit">Edit</button></td><td><button class="delete">Delete</button></td></tr>');
				 });
			},
			error: function() {
				alert('error loading orders');
			}
		})
	};

		//dodavanje novog artikla zajedno
	$('#snimiArtikl').on('click', function(){
		var artikal = {
			id: $id.val(),
			naziv: $naziv.val(),
			opis: $opis.val(),
			cena: $cena.val(),
			putanjaSlike: $putanjaSlike.val(),};
		if($id.val() ==''){
		console.log(artikal+' novi arikla')	
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci/'+ localStorage.getItem('id'),
			success: function(prodavac) {
				prodavac.artikli.push( artikal );
				var prodavacJSON = JSON.stringify(prodavac);
				$.ajax({
					headers: { 
						"Authorization": localStorage.getItem('token'),
			        	'Accept': 'application/json',
			        	'Content-Type': 'application/json' 
		    		},
					type: 'POST',
					url: 'http://localhost:8081/api/prodavci',
					data:  prodavacJSON,
					success: function() {
						$('#dodajArtiklDiv').hide();
		    			$('#listaArtikala').show();
		    			$('#dodajArtikl').show();
		    			$('#nazivArtikla').val('');
						$('#opis').val('');
						$('#cena').val('');
						$('#putanjaSlike').val('');
						$('#idProdavaca').val('');
						$('#id').val('');
						loadArtikls();	
					},
					error: function() {alert('error loading orders');}
				})
			},
			error: function() {alert('error loading orders');}
		})
		} else {
			console.log(artikal+' izmena arikla')
			var artikalJSON = JSON.stringify(artikal);
		$.ajax({
			headers: {
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' },
			type: 'POST',
			url: 'http://localhost:8081/api/artikli',
			data:  artikalJSON,
			success: function() {
				$('#dodajArtiklDiv').hide();
		   		$('#listaArtikala').show();
		   		$('#dodajArtikl').show();
		   		$('#nazivArtikla').val('');
				$('#opis').val('');
				$('#cena').val('');
				$('#putanjaSlike').val('');
				$('#idProdavaca').val('');
				$('#id').val('');
				loadArtikls();	
			},
			error: function() {alert('error loading orders');}
		});	
		}
	});
	
	
	//brisanje artikla
	$('#listaArtikala').on('click','.delete', function() {
    	var id = jQuery(this).closest('tr').attr('id');
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'DELETE',
			url: 'http://localhost:8081/api/artikli/' + id,
			success: function() {
				loadArtikls()
			},
			error: function() {
				alert('error loading orders');
			}
		});
	});
		
	//popunjavanje forme za izmena artikla
	$('#listaArtikala').on('click','.edit', function() {
    	var id = jQuery(this).closest('tr').attr('id');
		$.ajax({
		headers: {"Authorization": localStorage.getItem('token')},
		type: 'GET',
		url: 'http://localhost:8081/api/artikli/' + id,
		success: function(artikal) {
    		$('#listaArtikala').hide();
			$('#dodajArtikl').hide();
			$('#dodajArtiklDiv').show();
			$('#id').val(artikal.id);
			$('#nazivArtikla').val(artikal.naziv);
			$('#opis').val(artikal.opis);
			$('#cena').val(artikal.cena);
			$('#putanjaSlike').val(artikal.putanjaSlike);
		},
		error: function() {
			alert('error loading orders');
			}
		});
	});

	// poziv panela za dodavanje artikla
	$('#dodajArtikl').on('click', function(){
		    $('#listaArtikala').hide();
			$('#dodajArtikl').hide();
			$('#dodajArtiklDiv').show();
			$('#nazivArtikla').val('');
			$('#opis').val('');
			$('#cena').val('');
			$('#putanjaSlike').val('');
			$('#idProdavaca').val('');
			$('#id').val('');
	});
	
	// povratak an listu
	$('#odustani').on('click', function(){
		    $('#listaArtikala').show();
			$('#dodajArtikl').show();
			$('#dodajArtiklDiv').hide();
	});
	
	
	//popunjavanje forme za izmenu licnih pdataka
	$('#izmenaLicnihPodataka').on('click', function(){
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' 
    		},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci/'+ localStorage.getItem('id'),
			success: function(prodavac) {
				$('#izmenaPodatakaDiv').show();
				$('#listaArtikala').hide();
				$('#dodajArtikl').hide();
				$('#izmenaLicnihPodataka').hide();
				$('#idKorisnika').attr('value', prodavac.id);
				$('#sellerName').attr('value', prodavac.ime);
				$('#surname').attr('value', prodavac.prezime);
				$('#userName').attr('value', prodavac.userName);
				$('#password').attr('value', prodavac.password);
				$('#address').attr('value', prodavac.adresa);
				$('#naziv').attr('value', prodavac.naziv);
				$('#email').attr('value', prodavac.email);
			},	
			error: function() {
				alert('error saving');
			}
		});
	});
	
	//izmena licnih podataka
	$('#snimiPodatke').on('click', function(){
		if($('#password').val() == $('#oldPassword').val()){
		var prodavac= {
				id: $('#idKorisnika').val(),
				ime: $('#sellerName').val(),
				prezime: $('#surname').val(),
				userName: $('#userName').val(),
				password: $('#newPassword').val(),
				adresa: $('#address').val(),
				email: $('#email').val(),
				naziv: $('#naziv').val(),
		}	
		var korisnikJson = JSON.stringify(prodavac);
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/prodavci',
			data:  korisnikJson,
			success: function() {
				$('#oldPassword').val('');
				$('#newPassword').val('');
				$('#izmenaPodatakaDiv').hide();
				$('#listaArtikala').show();
				$('#dodajArtikl').show();
				$('#izmenaLicnihPodataka').show();
				console.log('podaci promenjeni')
			},	
			error: function() {
				alert('error saving');
			}
		});
		}
	});
	
		
		//odjava
		$('#odjava').on('click', function(){
			localStorage.clear();
			window.location.href = 'file:///C:/Users/iwanm/Documents/workspace-spring-tool-suite-4-4.11.0.RELEASE/OSA-3/src/main/java/com/project/osa/web/logIn.html';	
		});
		
	});