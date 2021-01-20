package com.techlead.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlead.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
