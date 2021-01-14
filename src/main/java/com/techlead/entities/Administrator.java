package com.techlead.entities;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {

	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}

	public Administrator(Long id, String name, String email, String password) {
		super(id, name, email, password, true);
	}

}
