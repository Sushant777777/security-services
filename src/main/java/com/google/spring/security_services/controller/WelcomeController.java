package com.google.spring.security_services.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to daily code buffer";
	}
}
