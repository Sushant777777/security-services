package com.google.spring.security_services.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.spring.security_services.UserService;
import com.google.spring.security_services.entity.User;
import com.google.spring.security_services.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserService service;

	@PostMapping("/saveUser")
	public User register(@RequestBody User user) {
		return service.register(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {

		String verify = service.verify(user);

		return verify;

		/*
		 * User byUsername = repository.findByUserName(user.getUserName()); if
		 * (!Objects.isNull(byUsername)) return "success"; else return "failure";
		 */
	}
}
