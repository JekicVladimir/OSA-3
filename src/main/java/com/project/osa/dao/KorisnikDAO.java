package com.project.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.osa.entity.Korisnik;


public interface KorisnikDAO extends JpaRepository<Korisnik, Integer> {


}
