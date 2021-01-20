package com.techlead.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techlead.services.ClientService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ClientService clientService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.anyRequest().permitAll().and().httpBasic().and().cors();
		        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	/*protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf()//.disable();
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}*/

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		/*
		 * auth.inMemoryAuthentication() .withUser("admin") .password("{noop}admin")
		 * .roles("USER");
		 */

		auth.userDetailsService(clientService).passwordEncoder(passwordEncoder);

	}

}
