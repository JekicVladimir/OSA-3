

$(function(){
	$('#listaArtikalaDiv').hide();
	$('#izmenaPodatakaDiv').hide();	
	$('#korpaDiv').hide();
	var $listaProdavaca =$('#listaProdavaca');
	var d = new Date();
	var ukupnaCena = 0;
	var date = moment(); 
	var newDate = date.format("YYYY-MM-DD"); 

	loadProdavci();
	 
	//ucitavanje liste prodavaca
	function loadProdavci() {		
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci',
			success: function(prodavci) {
				$('#listaProdavaca').empty();
				$('#listaProdavaca').append('<tr><th>Naziv</th><th>Adresa</th><th>Email</th><th>Posluje od</th></tr>');
				$.each(prodavci, function(i, prodavac) {
					$('#listaProdavaca tr:last').after('<tr id=' + prodavac.id +'><td>' + prodavac.naziv + '</td><td>' + prodavac.adresa + '</td><td>' + prodavac.email + '</td><td>' + prodavac.poslujeOd + '</td><td><button class="odaberiProdavca">Odaberi</button></tr>');
				});
			},
			error: function() {alert('error loading orders');}
		});
	}
		
	//odaber prodavca i prikazivanje liste artikala sa akciskim popustima
	$('#listaProdavaca').on('click','.odaberiProdavca', function() {
    	var id = jQuery(this).closest('tr').attr('id');
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci/' + id,
				success: function(prodavac) {
					$('#listaProdavacaDiv').hide();
					$('#listaArtikalaDiv').show();	
					$('#listaArtikala').empty();
					$('#listaArtikala').append('<tr><th>Naziv</th><th>Opis</th><th>Cena</th><th>Slika</th></tr>');
					$.each(prodavac.akcije, function(i, akcija) {
						var odKad = new Date(akcija.odKad);
						var doKad = new Date(akcija.doKad);
						//console.log(odKad);
						//console.log(doKad);
						if (odKad < d && d < doKad){ //aaaaaa
							//console.log("izmedju je");
						$.each(akcija.artikliNaAkciji, function(k,artikalNaAkciji) {
							var postojiNaAkciji = false;
							$("#listaArtikala tr.item").each(function() {
								if(artikalNaAkciji.id == this.id) {postojiNaAkciji = true;}
							});
							if(postojiNaAkciji == false){
								$('#listaArtikala tr:last').after('<tr class="item" id=' + artikalNaAkciji.id +'><td>' + artikalNaAkciji.naziv + '</td><td>' + artikalNaAkciji.opis + '</td><td>' + (artikalNaAkciji.cena * ((100 -akcija.procenat)/100)) + '</td><td>' + artikalNaAkciji.putanjaSlike+ '</td><td>Kolicna: <input type="text" name="kolicina" id="kolicina" value="1"></td><td><button class="dodavanjeArtiklaUKorpu">Dodaj u korpu</button></td><td>NA AKCIJI ' + akcija.procenat+ '%</td></tr>');	
							}
						});	
						}
					});	
					$.each(prodavac.artikli, function(i, artikl) {
						var postoji = false;
						//console.log("dolazni artikal sa brojem " +artikl.id)
						$("#listaArtikala tr.item").each(function() {
					  		//console.log("ARTIKAL NA LISTI sa brojem " + this.id)
							if(artikl.id == this.id) {postoji = true;}
						});
						if(postoji == false){
							//console.log("DODATA ARTIKAL " +artikl.id)
							$('#listaArtikala tr:last').after('<tr id=' + artikl.id +'><td>' + artikl.naziv + '</td><td>' + artikl.opis + '</td><td>' + artikl.cena + '</td><td>' + artikl.putanjaSlike+ '</td><td>Kolicna: <input type="text" name="kolicina" id="kolicina" value="1"></td><td><button class="dodavanjeArtiklaUKorpu">Dodaj u korpu</button></td></tr>');
						}								
					}); 
				},
				error: function() {alert('error loading orders');}
		});
	});
	
	//povratak an listu prodavaca
	$('#backToListaProdavaca').on('click', function(){
		$('#listaProdavacaDiv').show();
		$('#listaArtikalaDiv').hide();
		$('#listaKorpa').empty();
		$('#listaKorpa').append('<tr><th>Naziv</th><th>Cena</th><th>Kolicina</th><th>Ukupno</th></tr>');
	});
	
	//dodavanje u korpu 
	$('#listaArtikala').on('click','.dodavanjeArtiklaUKorpu', function() {
    	var id = jQuery(this).closest('tr').attr('id');
		var currentRow=$(this).closest("tr");
		var naziv =currentRow.find("td:eq(0)").html();
		var opis =currentRow.find("td:eq(1)").html();
		var cena =currentRow.find("td:eq(2)").html();
		var putanjaDoSlike =currentRow.find("td:eq(3)").html();
		var kolicina =currentRow.find("td:eq(4) input[name='kolicina']").val();
		ukupnaCena = ukupnaCena + (cena * kolicina);
		$('#listaKorpa tr:last').after('<tr class="zaBrisanje" id=' + id +'><td>' + naziv + '</td><td>' + opis + '</td><td>' + putanjaDoSlike + '</td><td>' + cena + '</td><td>' + kolicina + '</td><td>' +cena*kolicina + '</td><td><button class="izbaciIzKorpe">Izbaci</button></td></tr>');
		$('#ukupnaCena').remove();
		$('#korpaDiv').append('<h4 id="ukupnaCena">Ukupna cena: '+ ukupnaCena +'</h4>');

	});

	//prelaz na korpu
	$('#korpa').on('click', function(){
		$('#korpaDiv').show();
		$('#listaArtikalaDiv').hide();
	});
	
	//povratak an listu artikala
	$('#backToListaArtikala').on('click', function(){
		$('#korpaDiv').hide();
		$('#listaArtikalaDiv').show();
	});

	//brisanje iz korpe
	$('#listaKorpa').on('click','.izbaciIzKorpe', function() { //radi iz prveeeeeee
		var currentRow=$(this).closest("tr");
		var cenaZaUmanjivanje =currentRow.find("td:eq(3)").html();
		console.log("nesto"+cenaZaUmanjivanje);
		ukupnaCena = ukupnaCena-cenaZaUmanjivanje;
		$('#ukupnaCena').remove();
		$('#korpaDiv').append('<h4 id="ukupnaCena">Ukupna cena: '+ ukupnaCena +'</h4>');
		$(this).closest("tr").remove(); 
	});
	
	
	//pravljene porudzbine slogirano
	$('#zavrsiKupovinu').on('click', function(){
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' 
    		},
			type: 'GET',
			url: 'http://localhost:8081/api/kupci/'+ localStorage.getItem('id'),
		success: function(kupac) {
		console.log(kupac);
		var porudzbina= {
			"kupac": {
	            "id": kupac.id,
	            "ime": kupac.ime,
	            "prezime": kupac.prezime,
	            "userName": kupac.userName,
	            "password": kupac.password,
	            "blokiran": kupac.blokiran,
	            "tipKorisnika": "kupac",
	            "adresa": kupac.adresa},
			satnica: newDate,
			stavke: []
			};	
		$("#listaKorpa > tbody > tr").each(function() {
			var idArtikla = jQuery(this).closest('tr').attr('id');
			var currentRow=$(this).closest("tr");
			var nazivdArtikla =currentRow.find("td:eq(0)").html();
			var opisdArtikla =currentRow.find("td:eq(1)").html();
			var putanjaDoSlikdArtiklae =currentRow.find("td:eq(2)").html();
			var cenadArtikla =currentRow.find("td:eq(3)").html();
			var kolicina =currentRow.find("td:eq(4)").html();
			var stavka = {
				"id": '',
				"kolicina": kolicina,
				"artikal": {
					"id": idArtikla,
					"naziv": nazivdArtikla,
					"opis": opisdArtikla,
					"cena": cenadArtikla,
					"putanjaSlike": putanjaDoSlikdArtiklae
					}
				}
			if(stavka.kolicina >= 1){
			console.log(stavka);
			porudzbina.stavke.push(stavka);
			};
		});
		console.log(porudzbina);
		var porudzbinaJSON = JSON.stringify(porudzbina);
		console.log(porudzbinaJSON);
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
				'Accept': 'application/json',
		    	'Content-Type': 'application/json' 
				},
			type: 'POST',
			url: 'http://localhost:8081/api/porudzbine',
			data:  porudzbinaJSON,
			success: function() {
			console.log('Snimlljeno');
			},
			error: function() {alert('error loading');}
		});
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
			url: 'http://localhost:8081/api/kupci/'+ localStorage.getItem('id'),
			success: function(prodavac) {
				$('#izmenaPodatakaDiv').show();
				$('#listaProdavacaDiv').hide();
				$('#listaProdavacaDiv').hide();
				$('#izmenaLicnihPodataka').hide();
				$('#id').val(prodavac.id);
				$('#sellerName').val(prodavac.ime);
				$('#surname').val(prodavac.prezime);
				$('#userName').val(prodavac.userName);
				$('#password').val(prodavac.password);
				$('#address').val(prodavac.adresa);
			},	
			error: function() {alert('error saving');}
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
				adresa: $('#address').val(),
		}	
		var korisnikJson = JSON.stringify(prodavac);
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/kupci',
			data:  korisnikJson,
			success: function() {
				$('#oldPassword').val('');
				$('#newPassword').val('');
				$('#izmenaPodatakaDiv').hide();
				$('#listaProdavacaDiv').show();
				$('#listaProdavacaDiv').show();
				$('#izmenaLicnihPodataka').show();
				console.log('podaci promenjeni')
			},	
			error: function() {alert('error saving');}
		});
		}
	});
		
	//odjava
	$('#odjava').on('click', function(){
		localStorage.clear();
		window.location.href = 'file:///C:/Users/iwanm/Documents/workspace-spring-tool-suite-4-4.11.0.RELEASE/OSA-3/src/main/java/com/project/osa/web/logIn.html';	
	});
	
		
})
