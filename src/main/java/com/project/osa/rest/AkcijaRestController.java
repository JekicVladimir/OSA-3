package com.project.osa.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.osa.entity.Akcija;
import com.project.osa.service.Service;

@RestController
@RequestMapping("/api")
public class AkcijaRestController {
	
	private Service<Akcija> akcijaService;

	@Autowired
	public AkcijaRestController(Service<Akcija> theAkcijaService) {
		akcijaService = theAkcijaService;
	}
	
	@GetMapping("/akcije")
	public List<Akcija> findAll(){
		return akcijaService.findAll();
	}
	
	@GetMapping("/akcije/{theId}")
	public Optional<Akcija> findById(@PathVariable int theId){
		return akcijaService.findById(theId);
	}
	
	@PostMapping("/akcije")
	public String save( Akcija theAkcija) {
		akcijaService.save(theAkcija);
		return theAkcija.toString();
	}
	
	@DeleteMapping("/akcije/{theId}")
	public void delete(@PathVariable int theId) {
		akcijaService.delete(theId);
	}
	
}
