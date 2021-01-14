package com.techlead.entities;

import java.time.Instant;

import com.techlead.enums.BookStatus;

public class Book {

	private Long id;
	private String nome;
	private String author;
	private Instant dataCadastro;
	private Integer bookStatus;
	
	public Book() {}
	
	public Book(Long id, String nome, String author, Instant dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.author = author;
		this.dataCadastro = dataCadastro;
		setBookStatus(BookStatus.AVAILABLE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public BookStatus getBookStatus() {
		return BookStatus.valueOf(bookStatus);
	}
	
	public void setBookStatus(BookStatus bookStatus) {
		if(bookStatus != null) {
			this.bookStatus = bookStatus.getCode();
		}
	}
	
}
