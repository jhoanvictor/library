package com.techlead.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Client extends User {
	
	private static final long serialVersionUID = 1L;
	
	public Client() {
		super();
	}

	public Client(Long id, String name, String email, String password) {
		super(id, name, email, password, false, "ROLE_USER");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(authorities.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
