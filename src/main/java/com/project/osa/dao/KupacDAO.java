package com.project.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.osa.entity.Kupac;

public interface KupacDAO extends JpaRepository<Kupac, Integer> {

}
