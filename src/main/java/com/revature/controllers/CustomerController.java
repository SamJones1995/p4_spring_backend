package com.revature.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private final CustomerRepository customerRepository;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Customer customer) {
		
		if(customerRepository.existsByUsername(customer.getUsername()))
        {
            return ResponseEntity.status(400).body("Username already in use!");
        }
		
		return ResponseEntity.status(201).body(customerRepository.save(customer));
	}

}
