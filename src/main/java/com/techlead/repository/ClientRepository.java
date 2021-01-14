package com.techlead.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlead.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
}
