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

import com.project.osa.entity.Artikal;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api")
public class ArtikalRestController {

	private Service<Artikal> artikalService;
	
	@Autowired
	public ArtikalRestController(Service<Artikal> theArtikalService) {
		artikalService = theArtikalService;
	}
	
	@GetMapping("/artikli")
	public List<Artikal> findAll(){
		return artikalService.findAll();
	}
	
	@GetMapping("/artikli/{theId}")
	public Optional<Artikal> findById(@PathVariable int theId){
		return artikalService.findById(theId);
	}

	@PostMapping(value="/artikli/{theId}", consumes="application/json")
	public ResponseEntity<Artikal> saveWithId(@PathVariable int theId, @RequestBody Artikal theArtikal) {
		artikalService.saveWithId(theArtikal, theId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping(value="/artikli", consumes="application/json")
	public ResponseEntity<Artikal> save(@RequestBody Artikal theArtikal) {
		artikalService.save(theArtikal);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/artikli/{theId}")
	public void delete(@PathVariable int theId) {
		artikalService.delete(theId);
	}
	
	
	
	
	
	
}









