package com.project.osa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.StavkaDAO;
import com.project.osa.entity.Stavka;

@Service
public class StavkaServiceImpl implements com.project.osa.service.Service<Stavka> {

	private StavkaDAO stavkaRepository;
	
	@Autowired
	public StavkaServiceImpl(StavkaDAO theStavkaRepository) {
		stavkaRepository = theStavkaRepository;
	}
	
	@Override
	public List<Stavka> findAll() {
		return stavkaRepository.findAll();
	}

	@Override
	public Optional<Stavka> findById(int theId) {
		return stavkaRepository.findById(theId);
	}

	@Override
	public void save(Stavka theEntity) {
		stavkaRepository.save(theEntity);
	}

	
	@Override
	public void delete(int theId) {
		stavkaRepository.deleteById(theId);
	}

	@Override
	public List<Stavka> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Stavka theEntity, int theId) {
		// TODO Auto-generated method stub
		
	}

}
