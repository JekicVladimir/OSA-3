package com.project.osa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.AdministratorDAO;
import com.project.osa.entity.Administrator;

@Service
public class AdministratorServiceImpl implements com.project.osa.service.Service<Administrator> {

	private AdministratorDAO administratorRepository;
	
	@Autowired
	public AdministratorServiceImpl(AdministratorDAO theAdministratorRepository) {
		administratorRepository = theAdministratorRepository;
	}

	@Override
	public List<Administrator> findAll() {
		// TODO Auto-generated method stub
		return administratorRepository.findAll();
	}

	@Override
	public Optional<Administrator> findById(int theId) {
		// TODO Auto-generated method stub
		return administratorRepository.findById(theId);
	}

	@Override
	public void save(Administrator theEntity) {
		administratorRepository.save(theEntity);
		
	}

	@Override
	public void delete(int theId) {
		administratorRepository.deleteById(theId);
		
	}

	@Override
	public List<Administrator> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Administrator theEntity, int theId) {
		// TODO Auto-generated method stub
		
	}





}
