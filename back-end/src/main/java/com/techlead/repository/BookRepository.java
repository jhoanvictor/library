package com.techlead.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techlead.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value = "SELECT b.* FROM tb_book b where b.book_status = 1", nativeQuery = true)
	public List<Book> booksAvailable();
	
	@Query(value = "SELECT B.* FROM TB_BOOK B "
				+ "JOIN TB_ORDER O ON O.BOOK_ID = B.ID AND O.ORDER_STATUS = 1 "
				+ "JOIN TB_USER U ON O.CLIENT_ID = U.ID AND U.ID = ?1 "
				+ "WHERE B.BOOK_STATUS = 2", nativeQuery = true)
	public List<Book> booksRented(Long idClient);
	
}
