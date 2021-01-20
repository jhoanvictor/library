package com.techlead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlead.entities.Order;
import com.techlead.enums.BookStatus;
import com.techlead.enums.OrderStatus;
import com.techlead.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ClientService clientService;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}

	public Order insert(Order obj) {
		
		obj.setClient(clientService.findById(obj.getClient().getId()));
		obj.setBook(bookService.findById(obj.getBook().getId()));
		
		if (!obj.getBook().getBookStatus().equals(BookStatus.AVAILABLE)) {
			return null;
		}

		if(clientService.getTotalBooks(obj.getClient()) >= 5) {
			//cliente ja possue 5 livros alugados
			return null;
		}
		
		return repository.save(obj);
	}

	/**
	 * Verificar pedido e aprovar ou recusar o mesmo
	 * 
	 * @param obj
	 * @param status
	 * @return
	 */
	public Order handleVerifyOrder(Order obj, OrderStatus status) {

		obj = findById(obj.getId());
		
		modifyStatus(obj, status);

		if (obj.getOrderStatus().equals(OrderStatus.APPROVED)) {

			List<Order> orders = findAll();

			for (Order order : orders) {
				if (obj.getId() != order.getId() && obj.getBook().equals(order.getBook())) {
					modifyStatus(order, OrderStatus.REFUSED);
					repository.save(order);
				}
			}

			bookService.rentBook(obj.getBook());

		}
		return repository.save(obj);
	}

	private void modifyStatus(Order obj, OrderStatus status) {

		if (obj.getOrderStatus().equals(OrderStatus.PENDING)) {
			if (status.equals(OrderStatus.APPROVED)) {
				obj.setOrderStatus(OrderStatus.APPROVED);
			}
			if (status.equals(OrderStatus.REFUSED)) {
				obj.setOrderStatus(OrderStatus.REFUSED);
			}
		}
	}

}
