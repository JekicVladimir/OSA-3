package com.project.osa.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.osa.dao.ArtikalDAO;
import com.project.osa.dao.ProdavacDAO;
import com.project.osa.entity.Artikal;
import com.project.osa.entity.Prodavac;

@Service
public class ArtikliServiceImpl implements com.project.osa.service.Service<Artikal> {

	@Autowired
	private ProdavacDAO prodavacRepository;
	
	
	private ArtikalDAO artikalRepository;
	
	
	@Autowired
	public ArtikliServiceImpl(ArtikalDAO theArtikalRepository) {
		artikalRepository = theArtikalRepository;
	}
	
	@Override
	public List<Artikal> findAll() {
		return artikalRepository.findAll();
	}

	@Override
	public Optional<Artikal> findById(int theId) {
		return artikalRepository.findById(theId);
	}

	@Override
	public void save(Artikal theEntity) {
		artikalRepository.save(theEntity);
		
	}

	@Override
	public void delete(int theId) {
		artikalRepository.deleteById(theId);
		
	}

	@Override
	public List<Artikal> findAllWithId(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWithId(Artikal theEntity, int theId) {
		System.out.println(theId);
		Optional<Prodavac> prodavac = prodavacRepository.findById(theId);
		Prodavac pro = new Prodavac();
		pro.setId(prodavac.get().getId());
		pro.setAdresa(prodavac.get().getAdresa());
		pro.setBlokiran(prodavac.get().isBlokiran());
		pro.setEmail(prodavac.get().getEmail());
		pro.setIme(prodavac.get().getIme());
		pro.setNaziv(prodavac.get().getNaziv());
		pro.setPassword(prodavac.get().getPassword());
		pro.setPoslujeOd(prodavac.get().getPoslujeOd());
		pro.setPrezime(prodavac.get().getPrezime());
		pro.setUserName(prodavac.get().getUserName());
		pro.setTipKorisnika(prodavac.get().getTipKorisnika());
		pro.setAkcije(prodavac.get().getAkcije());
		pro.setArtikli(prodavac.get().getArtikli());
		//theEntity.setProdavac(pro);
		artikalRepository.save(theEntity);
		
	}
	
}


