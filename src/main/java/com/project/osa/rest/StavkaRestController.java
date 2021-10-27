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
import com.project.osa.entity.Stavka;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api")
public class StavkaRestController {
	
	private Service<Stavka> stavkaService;
	
	@Autowired
	public StavkaRestController(Service<Stavka> theStavkaService) {
		stavkaService = theStavkaService;
	}

	@GetMapping("/stavke")
	public List<Stavka> findAll(){
		return stavkaService.findAll();
	}
	
	@GetMapping("/stavke/{theId}")
	public Optional<Stavka> findById(@PathVariable int theId){
		return stavkaService.findById(theId);
	}
	
	@PostMapping(value="/stavke", consumes="application/json")
	public ResponseEntity<Stavka> save(@RequestBody Stavka theStavka) {
		stavkaService.save(theStavka);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/stavke/{theId}")
	public void delete(@PathVariable int theId) {
		stavkaService.delete(theId);
	}
	
	
}
