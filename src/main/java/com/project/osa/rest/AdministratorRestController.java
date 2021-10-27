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

import com.project.osa.entity.Administrator;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api/administratori")
public class AdministratorRestController {

	private Service<Administrator> administratorService;
	
	@Autowired
	public AdministratorRestController(Service<Administrator> theAdministratorService) {
	administratorService = theAdministratorService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Administrator>> findAll(){
		List<Administrator> administratori = administratorService.findAll();
		return new ResponseEntity<List<Administrator>>(administratori, HttpStatus.OK);
	}
	
	@GetMapping("/{theId}")
	public Optional<Administrator> findById(@PathVariable int theId){
		return administratorService.findById(theId);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<Administrator> save(@RequestBody Administrator theAdministrator) {
		administratorService.save(theAdministrator);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping("/{theId}")
	public void delete(@PathVariable int theId) {
		administratorService.delete(theId);
	}
	
	
	
}
