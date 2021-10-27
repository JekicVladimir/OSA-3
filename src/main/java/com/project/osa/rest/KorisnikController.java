
package com.project.osa.rest;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.osa.dto.KorisnikDTO;
import com.project.osa.entity.Korisnik;
import com.project.osa.service.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/api")
public class KorisnikController {

	@Autowired
	private Service<Korisnik> korisnikService;
	
	
	@PostMapping(value="/korisnici", consumes="application/json")
	public ResponseEntity<KorisnikDTO> login(@RequestBody KorisnikDTO korisnik) {
		
		//KorisnikDTO korisnik1 = new KorisnikDTO();
		List<Korisnik> listaKorsnika = korisnikService.findAll();
		
		//korisnik1.setUserName(korisnik.getUserName());
		//korisnik1.setPassword(korisnik.getPassword());
		for (Korisnik i : listaKorsnika) {
			if (i.getUserName().equals(korisnik.getUserName()) && i.getPassword().equals(korisnik.getPassword()) && i.isBlokiran() == false ){ // i ako nije blokiran
				
				korisnik.setId(i.getId());
				korisnik.setTipKorisnika("ROLE_" + i.getTipKorisnika().toUpperCase());
				String ROLE = korisnik.getTipKorisnika().toString();
				String username = korisnik.getUserName().toString();
				String token = getJWTToken(username, ROLE);
				korisnik.setToken(token);
				System.out.println(korisnik.getTipKorisnika());
				return new ResponseEntity<KorisnikDTO>(korisnik, HttpStatus.OK);
			}
		}
		return new ResponseEntity<KorisnikDTO>(HttpStatus.NOT_FOUND);
		}
	
	@GetMapping("/korisnici")
	public List<Korisnik> findAll(){
		return korisnikService.findAll();
	}
	
	private String getJWTToken(String username, String role) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(role);
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	
	
	
	
}
