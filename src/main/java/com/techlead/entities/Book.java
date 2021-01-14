package com.techlead.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.techlead.enums.BookStatus;

@Entity
@Table(name = "tb_book")
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String author;
	private Instant dataCadastro;
	private Integer bookStatus;
	
	@ManyToMany(mappedBy = "books")
	private Set<Order> orders = new HashSet<Order>();
	
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
	
	public Set<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
