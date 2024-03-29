package com.techlead.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.techlead.entities.Administrator;
import com.techlead.repository.AdministratorRepository;

@Configuration
@Profile("dev")
public class Config implements ApplicationRunner {

	@Autowired
	private AdministratorRepository admRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Administrator adm = new Administrator(null, "adm", "adm@email.com", "root");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		adm.setPassword(passwordEncoder.encode(adm.getPassword()));
		
		admRepository.save(adm);
		
	}

}
