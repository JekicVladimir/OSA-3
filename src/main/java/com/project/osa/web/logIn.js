

$(function(){
	$('#registracijaDiv').hide();
	$('#email').hide();
	$('#naziv').hide();
	$('#poslujeOd').hide();
	
		var $name =$('#name');
		var $surname =$('#surname');
		var $userName =$('#userName');
		var $password =$('#password');
		var $address =$('#address');
		var $emailInput =$('#emailInput');
		var $nazivInput =$('#nazivInput');
		var $poslujeOdInput =$('#poslujeOdInput');
	
	var $userNameLogIn =$('#userNameLogIn');
	var $passwordLogIn =$('#passwordLogIn');
		
		
		
		
		
		//prelazak an registarcionu formu	
		$('#toRegisterForm').on('click', function(){
			$('#registracijaDiv').show();
			$('#logInDiv').hide();
			$('#toRegisterForm').hide();
		});
		
		//povratak na log in
		$('#back').on('click', function(){
			$('#registracijaDiv').hide();
			$('#logInDiv').show();
			$('#toRegisterForm').show();
		});
		
		//odabir izmedju prodavca i kupca
		$('#prodavac').click(function() {
			if($('#prodavac').prop('checked')) {
			    $('#email').show();
			    $('#naziv').show();
			    $('#poslujeOd').show();
			} else {
			    $('#email').hide();
			    $('#naziv').hide();
			    $('#poslujeOd').hide();
			}
		});
		
		//registarcija
		$('#register').on('click', function(){
			//console.log($poslujeOdInput.val())
			if($('#prodavac').prop('checked')) {
			var prodavac= {
				ime: $name.val(),
				prezime: $surname.val(),
				userName: $userName.val(),
				password: $password.val(),
				adresa: $address.val(),
				tipKorisnika: "prodavac",
				email: $emailInput.val(),
				naziv: $nazivInput.val(),
				poslujeOd: $poslujeOdInput.val(),
				}	
			var korisnikJson = JSON.stringify(prodavac);
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/prodavci',
			data:  korisnikJson,
			success: function() {
				console.log("DODAT PRODAVAC!!!!!");
				$('#registracijaDiv').hide();
				$('#logInDiv').show();
				$('#toRegisterForm').show();
				},
			error: function() {
				alert('error saving');
				}
			});		
		}  else {
			var kupac = {
				ime: $name.val(),
				prezime: $surname.val(),
				userName: $userName.val(),
				password: $password.val(),
				adresa: $address.val(),
				tipKorisnika: "kupac",
				}	
			var korisnikJson = JSON.stringify(kupac);
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/kupci',
			data:  korisnikJson,
			success: function() {
				console.log("DODAT KUPAC!!!!!");
				$('#registracijaDiv').hide();
				$('#logInDiv').show();
				$('#toRegisterForm').show();
				},
			error: function() {
				alert('error saving');
				}
			});
			}	
		});	
		
		//logovanje 
		$('#logIn').on('click', function(){
		var user = {
			userName: $userNameLogIn.val(),
			password: $passwordLogIn.val(),
		}
		var userJson = JSON.stringify(user);
		
		$.ajax({
			    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    		},
			type: 'POST',
			url: 'http://localhost:8081/api/korisnici',
			data:  userJson,
			success: function(user) {
				localStorage.setItem('token', user.token);
				localStorage.setItem('id', user.id);
				localStorage.setItem('tipKorisnika', user.tipKorisnika);
				if (user.tipKorisnika == 'ROLE_PRODAVAC') {
					window.location.href = 'file:///C:/Users/iwanm/Documents/workspace-spring-tool-suite-4-4.11.0.RELEASE/OSA-3/src/main/java/com/project/osa/web/prodavci.html';
				} else if (user.tipKorisnika == 'ROLE_KUPAC') {
					window.location.href = 'file:///C:/Users/iwanm/Documents/workspace-spring-tool-suite-4-4.11.0.RELEASE/OSA-3/src/main/java/com/project/osa/web/kupci.html';
				} else if (user.tipKorisnika == 'ROLE_ADMINISTRATOR') {
					window.location.href = 'file:///C:/Users/iwanm/Documents/workspace-spring-tool-suite-4-4.11.0.RELEASE/OSA-3/src/main/java/com/project/osa/web/administrator.html';
				}
			},
			error: function() {
				alert('error saving');
			}
		});
		});
});
	