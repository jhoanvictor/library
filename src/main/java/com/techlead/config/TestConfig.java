package com.techlead.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.techlead.entities.Administrator;
import com.techlead.entities.Book;
import com.techlead.entities.Client;
import com.techlead.entities.Order;
import com.techlead.enums.OrderStatus;
import com.techlead.repository.AdministratorRepository;
import com.techlead.repository.BookRepository;
import com.techlead.repository.ClientRepository;
import com.techlead.repository.OrderRepository;
import com.techlead.services.BookService;
import com.techlead.services.OrderService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private AdministratorRepository admRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService service;
	
	@Autowired
	private BookService bookService;

	@Override
	public void run(String... args) throws Exception {

		Administrator adm = new Administrator(null, "adm", "adm@email.com", "root");
		Client c1 = new Client(null, "Jhoan", "jhoan@email.com", "12345");
		Client c2 = new Client(null, "Dani", "dani@email.com", "12345");

		Book b1 = new Book(null, "JAVA com Spring", "Autor Desconhecido", Instant.now());
		Book b2 = new Book(null, "PHP", "Autor Desconhecido", Instant.now());
		Book b3 = new Book(null, "Python", "Autor Desconhecido", Instant.now());
		Book b4 = new Book(null, "HTML e CSS", "Autor Desconhecido", Instant.now());
		Book b5 = new Book(null, "Angular", "Autor Desconhecido", Instant.now());

		bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));

		Order o1 = new Order(null, c1, b1);
		Order o2 = new Order(null, c2, b1);
		Order o3 = new Order(null, c1, b3);
		Order o4 = new Order(null, c2, b4);
		Order o5 = new Order(null, c2, b3);
		Order o6 = new Order(null, c2, b3);

		admRepository.save(adm);
		clientRepository.saveAll(Arrays.asList(c1, c2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5, o6));

		/*service.handleVerifyOrder(o1, OrderStatus.REFUSED);
		service.handleVerifyOrder(o2, OrderStatus.APPROVED);
		service.handleVerifyOrder(o5, OrderStatus.APPROVED);
		service.handleVerifyOrder(o3, OrderStatus.APPROVED);
		service.handleVerifyOrder(o6, OrderStatus.APPROVED);
		service.handleVerifyOrder(o4, OrderStatus.APPROVED);
		
		bookService.devolutionBook(b3);*/

	}

}
