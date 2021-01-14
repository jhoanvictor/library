package com.techlead.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlead.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
}
