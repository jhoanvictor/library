package com.techlead.endpoint;

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

import com.techlead.entities.Administrator;
import com.techlead.services.AdminService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping(value = "/admin")
public class AdminEndPoint {

	@Autowired
	private AdminService service;

	@GetMapping
	public ResponseEntity<List<Administrator>> findAll() {
		List<Administrator> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Administrator> findById(@PathVariable Long id) {
		Administrator obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/auth")
	public UserDetails Administrator(@RequestBody Administrator admin) {
		return service.loadUserByUsername(admin.getEmail());
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Administrator> login(@RequestBody Administrator Administrator) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		List<Administrator> list = service.findAll();
		for (Administrator c : list) {
			if (c.getEmail().equals(Administrator.getEmail())
					&& passwordEncoder.matches(Administrator.getPassword(), c.getPassword())) {
				return ResponseEntity.ok().body(service.findById(c.getId()));
			}
		}
		return null;
	}

}
