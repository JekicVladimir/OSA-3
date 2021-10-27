

$(function(){
	$('#izmenaPodatakaDiv').hide();
	loadUsers();
	
	function loadUsers() {
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/korisnici',
			success: function(korisnic) {
				$('#listaKorisnika').empty();
				$('#listaKorisnika').append('<tr><th>Ime</th><th>Prezime</th><th>Korisnkicko ime</th><th>Tip korisnika</th><th>Blokiran</th></tr>');
				$.each(korisnic, function(i, korisnik) {
					if (korisnik.tipKorisnika == "kupac" || korisnik.tipKorisnika == "prodavac") {
					$('#listaKorisnika tr:last').after('<tr id=' + korisnik.id +'><td>' + korisnik.ime + '</td><td>' + korisnik.prezime + '</td><td>' + korisnik.userName + '</td><td>' + korisnik.tipKorisnika+ '</td><td>' + korisnik.blokiran+ '</td><td><button class="blokiraj">Blokiraj</button></td></tr>');
					}
				});
			},
			error: function() {
				alert('error loading orders');
			}
		})
	};
	
	//blokiranje korisnika
	$('#listaKorisnika').on('click','.blokiraj', function() {
    	var id = jQuery(this).closest('tr').attr('id');
    	var currentRow = $(this).closest("tr");
    	var tipKorisnika = currentRow.find("td:eq(3)").html();
    	console.log(tipKorisnika);
    	
    	var priprema = {
			id: id,
			blokiran: 'true',
			}
		var korisnik = JSON.stringify(priprema);
		var tip;
		if(tipKorisnika == "kupac"){
			tip = "kupci";
		} else if (tipKorisnika == "prodavac"){
			tip = "prodavci";
			}
		$.ajax({
		headers: {
			"Authorization": localStorage.getItem('token'),
			'Accept': 'application/json',
        	'Content-Type': 'application/json' 
		},
		type: 'POST',
		url: 'http://localhost:8081/api/'+ tip,
		data:  korisnik,
		success: function() {
			console.log('BLOKIRAN');
			loadUsers();
		},
		error: function() {
			alert('error loading orders');
			}
		});
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
			url: 'http://localhost:8081/api/administratori/'+ localStorage.getItem('id'),
			success: function(prodavac) {
				$('#izmenaPodatakaDiv').show();
				$('#listaKorisnikaDiv').hide();
				$('#izmenaLicnihPodataka').hide();
				$('#id').val(prodavac.id);
				$('#sellerName').val(prodavac.ime);
				$('#surname').val(prodavac.prezime);
				$('#userName').val(prodavac.userName);
				$('#password').val(prodavac.password);
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
				id: $('#id').val(),
				ime: $('#sellerName').val(),
				prezime: $('#surname').val(),
				userName: $('#userName').val(),
				password: $('#newPassword').val(),
		}	
		console.log(prodavac)
		var korisnikJson = JSON.stringify(prodavac);
		console.log(korisnikJson)
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/administratori',
			data:  korisnikJson,
			success: function() {
				$('#oldPassword').val('');
				$('#newPassword').val('');
				$('#izmenaPodatakaDiv').hide();
				$('#listaKorisnikaDiv').show();
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