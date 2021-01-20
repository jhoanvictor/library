package com.techlead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techlead.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	//@Query(value = "SELECT U.* FROM TB_USER U WHERE U.EMAIL = '?1%' ", nativeQuery = true)
	Client findByEmail(String email);
	
	@Query(value = "SELECT COUNT(U.ID) FROM TB_BOOK B "
			+ "JOIN TB_ORDER O ON O.BOOK_ID = B.ID AND O.ORDER_STATUS = 1 "
			+ "JOIN TB_USER U ON O.CLIENT_ID = U.ID AND U.ID = ?1 "
			+ "WHERE B.BOOK_STATUS = 2", nativeQuery = true)
	public Integer totalBooks(Long idClient);
	
}
