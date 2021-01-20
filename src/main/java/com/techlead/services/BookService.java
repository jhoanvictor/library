package com.techlead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlead.entities.Book;
import com.techlead.entities.Client;
import com.techlead.enums.BookStatus;
import com.techlead.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public List<Book> findAll() {
		return repository.findAll();
	}
	
	public List<Book> booksRented(Client client){
		return repository.booksRented(client.getId());
	}
	
	public List<Book> booksAvailable(){
		return repository.booksAvailable();
	}

	public Book findById(Long id) {
		Optional<Book> obj = repository.findById(id);
		return obj.get();
		// () -> new ResourceNotFoundException(id)
	}

	public Book insert(Book obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Book update(Long id, Book obj) {
		Book entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Book entity, Book obj) {
		entity.setName(obj.getName());
		entity.setAuthor(obj.getAuthor());
	}
	
	public Book rentBook(Book book) {
		if (book.getBookStatus().equals(BookStatus.AVAILABLE)) {
			book.setBookStatus(BookStatus.UNAVAILABLE);
		}
		return repository.save(book);
	}

	public Book devolutionBook(Book book) {
		if (book.getBookStatus().equals(BookStatus.UNAVAILABLE)) {
			book.setBookStatus(BookStatus.AVAILABLE);
		}
		return repository.save(book);
	}

}
