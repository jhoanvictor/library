package com.techlead.entities;

public class Client extends User{

	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}

	public Client(Long id, String name, String email, String password) {
		super(id, name, email, password, false);
	}
	
}
