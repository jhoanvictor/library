package com.techlead.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techlead.entities.Order;
import com.techlead.enums.OrderStatus;
import com.techlead.repository.OrderRepository;

public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public void approveOrRefuse(Order obj) {
		if(obj.getOrderStatus().equals(OrderStatus.PENDING)) {
			obj.setOrderStatus(OrderStatus.APPROVED);
		}
	}
	
}
