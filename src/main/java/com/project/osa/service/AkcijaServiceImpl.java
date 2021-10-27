package com.project.osa.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.AkcijaDAO;
import com.project.osa.entity.Akcija;

@Service
public class AkcijaServiceImpl implements com.project.osa.service.Service<Akcija> {

	private AkcijaDAO akcijaRepository;
	
	@Autowired
	public AkcijaServiceImpl(AkcijaDAO theAkcijaRepository) {
		akcijaRepository = theAkcijaRepository;
	}
	
	@Override
	public List<Akcija> findAll() {
		// TODO Auto-generated method stub
		return akcijaRepository.findAll();
	}

	@Override
	public Optional<Akcija> findById(int theId) {
		// TODO Auto-generated method stub
		return akcijaRepository.findById(theId);
	}

	@Override
	public void save(Akcija theEntity) {
		akcijaRepository.save(theEntity);
		
	}

	@Override
	public void delete(int theId) {
		akcijaRepository.deleteById(theId);
		
	}

	@Override
	public List<Akcija> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Akcija theEntity, int theId) {
		// TODO Auto-generated method stub
		
	}
}
