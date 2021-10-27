package com.project.osa.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.osa.entity.Prodavac;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api")
public class ProdavacRestController {

	private Service<Prodavac> prodavacService;
	
	
	@Autowired
	public ProdavacRestController(Service<Prodavac> theKupacService) {
		prodavacService = theKupacService;
	}
	
	@GetMapping("/prodavci")
	public List<Prodavac> findAll(){
		return prodavacService.findAll();
	}
	
	@GetMapping("/prodavci/{theId}")
	public Optional<Prodavac> findById(@PathVariable int theId) {
		return prodavacService.findById(theId);
	}
	
	@PostMapping("/prodavci")
	public ResponseEntity<Prodavac> save(@RequestBody Prodavac theProdavac) {
		prodavacService.save(theProdavac);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/prodavci/{theId}")
	public void delete(@PathVariable int theId) {
		prodavacService.delete(theId);
	}
}
