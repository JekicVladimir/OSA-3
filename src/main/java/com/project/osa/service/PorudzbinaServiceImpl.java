package com.project.osa.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.osa.dao.PorudzbinaDAO;
import com.project.osa.entity.Porudzbina;


@Service
public class PorudzbinaServiceImpl implements com.project.osa.service.Service<Porudzbina> {

	
	private PorudzbinaDAO porudzbinaRepository;
	
	@Autowired
	public PorudzbinaServiceImpl(PorudzbinaDAO thePorudzbinaRepository) {
		porudzbinaRepository = thePorudzbinaRepository;
	}
	
	@Override
	public List<Porudzbina> findAll() {
		return porudzbinaRepository.findAll();
	}


	@Override
	public Optional<Porudzbina> findById(int theId) {
		return porudzbinaRepository.findById(theId);
	}

	@Override
	public void save(Porudzbina theEntity) {
		porudzbinaRepository.save(theEntity);
		
	}

	@Override
	public void delete(int theId) {
		porudzbinaRepository.deleteById(theId);
		
	}


	@Override
	public List<Porudzbina> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Porudzbina theEntity, int theId) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
}
