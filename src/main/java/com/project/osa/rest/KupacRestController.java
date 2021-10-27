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

import com.project.osa.entity.Kupac;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api")
public class KupacRestController {

	private Service<Kupac> kupacService;
	
	@Autowired
	public KupacRestController(Service<Kupac> theKupacService) {
		kupacService = theKupacService;
	}
	
	@GetMapping("/kupci")
	public List<Kupac> findAll(){
		return kupacService.findAll();
	}
	
	@GetMapping("/kupci/{theId}")
	public Optional<Kupac> findById(@PathVariable int theId) {
		return kupacService.findById(theId);
	}
	
	@PostMapping(value="/kupci", consumes="application/json")
	public ResponseEntity<Kupac> save(@RequestBody Kupac theKupac) {
		kupacService.save(theKupac);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/kupci/{theId}")
	public void delete(@PathVariable int theId) {
		kupacService.delete(theId);
	}
	
}
