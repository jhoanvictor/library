package com.techlead.endpoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techlead.entities.Client;
import com.techlead.services.ClientService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/clients")
public class ClientEndPoint {

	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Client> register(@RequestBody Client obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PostMapping(value = "/auth")
	public UserDetails client(@RequestBody Client client) {
		return service.loadUserByUsername(client.getEmail());
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Client> login(@RequestBody Client client) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		List<Client> list = service.findAll();
		for (Client c : list) {
			if (c.getEmail().equals(client.getEmail())
					&& passwordEncoder.matches(client.getPassword(), c.getPassword())) {
				return ResponseEntity.ok().body(service.findById(c.getId()));
			}
		}
		return null;
	}


}
