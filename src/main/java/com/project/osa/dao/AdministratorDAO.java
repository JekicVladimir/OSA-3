package com.project.osa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.osa.entity.Administrator;

public interface AdministratorDAO extends JpaRepository<Administrator, Integer> {

}
