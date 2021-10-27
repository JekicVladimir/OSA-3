package com.project.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.osa.entity.Akcija;

public interface AkcijaDAO extends JpaRepository<Akcija, Integer> {


}
