package com.techlead.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlead.entities.Client;
import com.techlead.repository.ClientRepository;
import com.techlead.services.exceptions.DatabaseException;

@Service
public class ClientService implements UserDetailsService {

	@Autowired
	private ClientRepository repository;

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		return obj.get();
	}

	public Client insert(Client obj) {
		if (obj.getName().isEmpty()) {
			throw new DatabaseException("Name is required");
		}

		if (obj.getEmail().isEmpty()) {
			throw new DatabaseException("Email is required");
		}

		if (obj.getPassword().isEmpty()) {
			throw new DatabaseException("Password is required");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		obj.setPassword(passwordEncoder.encode(obj.getPassword()));

		return repository.save(obj);
	}

	public Integer getTotalBooks(Client client) {
		return repository.totalBooks(client.getId());
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		return Optional.ofNullable(repository.findByEmail(email))
				.orElseThrow(() -> new UsernameNotFoundException("Client not found"));
	}
	
}
