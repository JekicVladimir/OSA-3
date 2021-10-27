package com.project.osa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.ProdavacDAO;
import com.project.osa.entity.Prodavac;

@Service
public class ProdavacServiceImpl implements com.project.osa.service.Service<Prodavac> {


	private ProdavacDAO prodavacRepository;
	
	@Autowired
	public ProdavacServiceImpl(ProdavacDAO theProdavacRepository) {
		prodavacRepository = theProdavacRepository;
	}

	@Override
	public List<Prodavac> findAll() {
		return prodavacRepository.findAll();
	}

	@Override
	public Optional<Prodavac> findById(int theId) {
		return prodavacRepository.findById(theId);
	}

	@Override
	public void save(Prodavac theEntity) {
		prodavacRepository.save(theEntity);
		
	}

	@Override
	public void delete(int theId) {
		prodavacRepository.deleteById(theId);	
	}

	@Override
	public List<Prodavac> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Prodavac theEntity, int theId) {
		// TODO Auto-generated method stub
		
	}

}