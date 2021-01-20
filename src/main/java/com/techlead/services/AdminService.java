package com.techlead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techlead.entities.Administrator;
import com.techlead.repository.AdministratorRepository;

@Service
public class AdminService implements UserDetailsService {

	@Autowired
	private AdministratorRepository repository;

	public List<Administrator> findAll() {
		return repository.findAll();
	}

	public Administrator findById(Long id) {
		Optional<Administrator> obj = repository.findById(id);
		return obj.get();
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		return Optional.ofNullable(repository.findByEmail(email))
				.orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
	}
	
}
