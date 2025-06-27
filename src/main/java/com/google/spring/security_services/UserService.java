package com.google.spring.security_services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.spring.security_services.entity.User;
import com.google.spring.security_services.repository.UserRepository;
import com.google.spring.security_services.service.JWTService;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	public User register(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public String verify(User user) {

		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

		// User byUsername = userRepository.findByUserName(user.getUserName());
		if (authenticate.isAuthenticated())
			return jwtService.geneateToken(user);
		else
			return "failure";

	}
}
