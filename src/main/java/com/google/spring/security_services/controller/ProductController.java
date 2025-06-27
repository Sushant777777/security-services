package com.google.spring.security_services.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.spring.security_services.model.ProductDTO;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class ProductController {

	List<ProductDTO> list = new ArrayList<ProductDTO>();

	ProductController() {
		list.add(new ProductDTO(101, "Iphone", "100000"));
		list.add(new ProductDTO(102, "MacBook", "150000"));
		list.add(new ProductDTO(103, "MAC Mini", "60000"));
	}

	@GetMapping
	public List<ProductDTO> getAllProduct() {
		return list;
	}

	@PostMapping
	public ProductDTO saveProduct(ProductDTO productDTO) {
		list.add(productDTO);
		return productDTO;
	}

	@GetMapping("/csrf")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
}
