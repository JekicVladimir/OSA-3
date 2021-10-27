package com.project.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.osa.entity.Prodavac;

public interface ProdavacDAO extends JpaRepository<Prodavac, Integer> {

}
