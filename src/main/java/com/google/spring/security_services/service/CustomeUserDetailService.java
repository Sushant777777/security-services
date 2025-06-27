package com.google.spring.security_services.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.spring.security_services.entity.User;
import com.google.spring.security_services.repository.UserRepository;

@Component
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User byUserName = repository.findByUserName(username);
		if (Objects.isNull(byUserName)) {
			throw new UsernameNotFoundException("User not available");
		}

		return new CustomeUserDetails(byUserName);
	}

}
