package com.techlead.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlead.entities.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
	
}
