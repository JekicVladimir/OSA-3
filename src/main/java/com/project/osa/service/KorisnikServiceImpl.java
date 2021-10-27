package com.project.osa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.KorisnikDAO;
import com.project.osa.entity.Korisnik;

@Service
public class KorisnikServiceImpl implements com.project.osa.service.Service<Korisnik> {

	@Autowired
	private KorisnikDAO KorisnikRepository;
	
	@Override
	public List<Korisnik> findAll() {
		// TODO Auto-generated method stub
		return KorisnikRepository.findAll();
	}

	@Override
	public Optional<Korisnik> findById(int theId) {
		// TODO Auto-generated method stub
		return KorisnikRepository.findById(theId);
	}

	@Override
	public void save(Korisnik theEntity) {
		KorisnikRepository.save(theEntity);
		
	}

	@Override
	public void delete(int theId) {
		KorisnikRepository.deleteById(theId);
		
	}

	@Override
	public List<Korisnik> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Korisnik theEntity, int theId) {
		// TODO Auto-generated method stub
		
	} 

}