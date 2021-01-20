package com.techlead.endpoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techlead.entities.Book;
import com.techlead.entities.Client;
import com.techlead.services.BookService;
import com.techlead.services.ClientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/books")
public class BookEndPoint {

	@Autowired
	private BookService service;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<Book>> findAll() {
		List<Book> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Book obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/rent")
	public ResponseEntity<List<Book>> booksAvailable() {
		List<Book> list = service.booksAvailable();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/rented/{idClient}")
	public ResponseEntity<List<Book>> booksRented(@PathVariable Long idClient) {
		Client obj = clientService.findById(idClient);
		List<Book> list = service.booksRented(obj);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Book> register(@RequestBody Book obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/rent/{id}")
	public ResponseEntity<Book> rentBook(@PathVariable Long id) {
		Book obj = service.findById(id);
		obj = service.rentBook(obj);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/devolution/{id}")
	public ResponseEntity<Book> devolutionBook(@PathVariable Long id) {
		Book obj = service.findById(id);
		obj = service.rentBook(obj);
		return ResponseEntity.ok().body(obj);
	}

}
