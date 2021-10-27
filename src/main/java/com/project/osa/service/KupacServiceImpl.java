package com.project.osa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.KupacDAO;
import com.project.osa.entity.Kupac;

@Service
public class KupacServiceImpl implements com.project.osa.service.Service<Kupac> {

	private KupacDAO kupacRepository;
	
	@Autowired
	public KupacServiceImpl(KupacDAO theKupacRepository) {
		kupacRepository = theKupacRepository;
	}
	
	@Override
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}

	@Override
	public Optional<Kupac> findById(int theId) {
		return kupacRepository.findById(theId);
	}

	@Override
	public void save(Kupac theKupac) {
		kupacRepository.save(theKupac);
	}

	@Override
	public void delete(int theId) {
		kupacRepository.deleteById(theId);

	}

	@Override
	public List<Kupac> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Kupac theEntity, int theId) {
		// TODO Auto-generated method stub
		
	}

}
