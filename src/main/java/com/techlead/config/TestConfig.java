package com.techlead.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.techlead.entities.Administrator;
import com.techlead.entities.Book;
import com.techlead.entities.Client;
import com.techlead.entities.Order;
import com.techlead.enums.BookStatus;
import com.techlead.enums.OrderStatus;
import com.techlead.repository.AdministratorRepository;
import com.techlead.repository.BookRepository;
import com.techlead.repository.ClientRepository;
import com.techlead.repository.OrderRepository;

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
		
		Set<Book> books = new HashSet<Book>();
		
		Order o1 = new Order(null, c1, books);
		o1.getBook().add(b1);
		o1.getBook().add(b3);
		o1.getBook().add(b5);
		o1.setOrderStatus(OrderStatus.APPROVED);
		b1.setBookStatus(BookStatus.UNAVAILABLE);
		b3.setBookStatus(BookStatus.UNAVAILABLE);
		b5.setBookStatus(BookStatus.UNAVAILABLE);
		
		admRepository.save(adm);
		clientRepository.saveAll(Arrays.asList(c1, c2));
		bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));
		orderRepository.saveAll(Arrays.asList(o1));

	}

}
