	
		console.log(porudzbina);
		var porudzbinaJSON = JSON.stringify(porudzbina);
		console.log(porudzbinaJSON);	
		$("#listaKorpa tr.item").each(function() {
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
			porudzbina.stavke.push(stavka);
		});
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
		})
	
	
	
	
		var stavka0 = {
                "id": '',
                "kolicina": 2,
                "artikal": {
                    "id": 1,
                    "naziv": "Jabuka",
                    "opis": "123",
                    "cena": 123.0,
                    "putanjaSlike": "123"}}
		var stavka1 = {
			"id": '',
			"kolicina": 2,
			"artikal": {
				"id": 1,
				"naziv": "Jabuka",
				"opis": "123",
				"cena": 123.0,
				"putanjaSlike": "123"}}
		var stavka2 = {
             "id": '',
             "kolicina": 2,
             "artikal": {
                "id": 1,
                "naziv": "Jabuka",
                "opis": "123",
                "cena": 123.0,
      	        "putanjaSlike": "123"}}
		porudzbina.stavke.push(stavka0);
		porudzbina.stavke.push(stavka1);
		porudzbina.stavke.push(stavka2);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		//pravljene porudzbine slogirano
	$('#zavrsiKupovinu').on('click', function(){
		var porudzbina= {
			satnica: newDate
			};
		//console.log(porudzbina)	
		var porudzbinaJson = JSON.stringify(porudzbina);
		//console.log(porudzbinaJson)	
		
		
		
		
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json',
		        "Authorization": localStorage.getItem('token')},
			type: 'POST',
			url: 'http://localhost:8081/api/porudzbine/' + localStorage.getItem('id'),
			data:  porudzbinaJson,
			success: function() {
				$.ajax({
					headers: { 
						'Accept': 'application/json',
						'Content-Type': 'application/json',
						"Authorization": localStorage.getItem('token') },
					type: 'GET',
					url: 'http://localhost:8081/api/porudzbineWithId/' + localStorage.getItem('id'),
					success: function(porudzbine) {
						$.each(porudzbine, function(i, porudzbina) {
							console.log('i je ' +i)
							console.log('PORUDZBINA ' +porudzbina+ ' KRAJ PORUDZBINE')
							if (idPoslednjePorudzbine < porudzbina.id){
								idPoslednjePorudzbine = porudzbina.id;
							}
						})
						console.log('id poslednji '+idPoslednjePorudzbine);
						$("#listaArtikala tr.item").each(function() {
							var idArtikla = jQuery(this).closest('tr').attr('id');
							var currentRow=$(this).closest("tr");
							var kolicina =currentRow.find("td:eq(2)").html();
							var priprema = {
								kolicina: kolicina,
								porudzbina_id:idPoslednjePorudzbine,
								artikal_id:idArtikla
							};
							var stavka = JSON.stringify(priprema);
								$.ajax({
									headers: { 
										'Accept': 'application/json',
										'Content-Type': 'application/json',
										"Authorization": localStorage.getItem('token')},
									type: 'POST',
									url: 'http://localhost:8081/api/porudzbine',
									data:  stavka,
									success: function() {console.log("stavka dodata" + stavka)},
									error: function() {alert('error adding stavka');},
								});
							
						})		
					},
					error: function() {alert('error geting porudzbine');},
				});		
			},
			error: function() {alert('error saving porudzbine');}
		});
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//snimanje artikla
	$('#snimiArtikl111').on('click', function(){ ////1111 ne valja
		if($id.val() !=''){ //nepotrebno
			var priprema = {
				id: $id.val(),
				naziv: $naziv.val(),
				opis: $opis.val(),
				cena: $cena.val(),
				putanjaSlike: $putanjaSlike.val(),
			};
		} else {
			var priprema = {
				naziv: $naziv.val(),
				opis: $opis.val(),
				cena: $cena.val(),
				putanjaSlike: $putanjaSlike.val(),
			};
		}
		var artikl = JSON.stringify(priprema);
		$.ajax({
			headers: { 
				"Authorization": localStorage.getItem('token'),
	        	'Accept': 'application/json',
	        	'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/artikli/' + idProdavca, //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			data:  artikl,
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
			error: function() {
				alert('error saving');
			}
		});
	});
	
	
	
	
	
	
			//izmena artikla
	$('#izmeniArtikl').on('click', function(){
		var artikal = {
			id: $id.val(),
			naziv: $naziv.val(),
			opis: $opis.val(),
			cena: $cena.val(),
			putanjaSlike: $putanjaSlike.val(),};		
		var artikalJSON = JSON.stringify(artikal);
		console.log(artikal)
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
	});
	
	
	//dodavanje novog artikla
	$('#snimiArtikl').on('click', function(){
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci/'+ localStorage.getItem('id'),
			success: function(prodavac) {
				var artikal = {
					id: $id.val(),
					naziv: $naziv.val(),
					opis: $opis.val(),
					cena: $cena.val(),
					putanjaSlike: $putanjaSlike.val(),};
				//console.log(prodavac)
				console.log(artikal)
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
	});
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		//dodavanje novog artikla zajedno
	$('#snimiArtikl').on('click', function(){
		var artikal = {
			id: $id.val(),
			naziv: $naziv.val(),
			opis: $opis.val(),
			cena: $cena.val(),
			putanjaSlike: $putanjaSlike.val(),};
		if($id.val() !=''){
		console.log(artikal+'if novi arikla')	
		$.ajax({
			headers: {"Authorization": localStorage.getItem('token')},
			type: 'GET',
			url: 'http://localhost:8081/api/prodavci/'+ localStorage.getItem('id'),
			success: function(prodavac) {
				//console.log(prodavac)
				//console.log(artikal)
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
		//kraj if-a
		} else {
			console.log(artikal+'else izmena arikla')
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
		} // kraj elsea
	});