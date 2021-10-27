package com.project.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.osa.entity.Stavka;

public interface StavkaDAO extends JpaRepository<Stavka, Integer> {

}
