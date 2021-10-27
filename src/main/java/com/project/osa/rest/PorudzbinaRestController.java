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
import com.project.osa.entity.Porudzbina;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api")
public class PorudzbinaRestController {
	
	private Service<Porudzbina> porudzbinaService;
	
	@Autowired
	public PorudzbinaRestController(Service<Porudzbina> thePorudzbinaService) {
		porudzbinaService = thePorudzbinaService;
	}
	
	@GetMapping("/porudzbine")
	public List<Porudzbina> findAll(){
		return porudzbinaService.findAll();
	}
	
	@GetMapping("/porudzbineWithId/{theId}")
	public List<Porudzbina> findAllWithId(@PathVariable int theId){
		return porudzbinaService.findAllWithId(theId);
	}
	
	@GetMapping("/porudzbine/{theId}")
	public Optional<Porudzbina> findById(@PathVariable int theId){
		return porudzbinaService.findById(theId);
	}
	
	@PostMapping(value="/porudzbine/{theId}", consumes="application/json")
	public ResponseEntity<Porudzbina> saveWithId(@PathVariable int theId, @RequestBody Porudzbina thePorudzbina) {
		porudzbinaService.saveWithId(thePorudzbina, theId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@PostMapping(value="/porudzbine", consumes="application/json")
	public ResponseEntity<Porudzbina> save(@RequestBody Porudzbina thePorudzbina) {
		porudzbinaService.save(thePorudzbina);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/porudzbine/{theId}")
	public void delete(@PathVariable int theId) {
		porudzbinaService.delete(theId);
	}
}
