package com.hughsy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hughsy.domain.Customer;
import com.hughsy.repository.CustomerRepository;
import com.hughsy.security.CustomUserDetails;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder encoder = passwordEncoder();
		Customer customer = customerRepo.findByEmailAddress(username);
		if(customer == null) {
			throw new UsernameNotFoundException("Username "+ username + " not found.");
		}else {
			return new CustomUserDetails(customer, encoder);
		}
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
