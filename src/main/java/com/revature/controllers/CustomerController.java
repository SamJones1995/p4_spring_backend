package com.revature.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.LoginRequest;
import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	private final CustomerRepository customerRepository;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Customer> optional = customerRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.status(400).body("Login failed");
        }

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        

        return ResponseEntity.ok().build();
    }
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Customer customer) {
		
		if(customerRepository.existsByUsername(customer.getUsername()))
        {
            return ResponseEntity.status(400).body("Username already in use!");
        }
		
		return ResponseEntity.status(201).body(customerRepository.save(customer));
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<?> getCustomerByUsername(@PathVariable("username") String username) {
		if (!customerRepository.existsByUsername(username)) {
			
			return ResponseEntity.status(400).body("User not found");
		}
		return ResponseEntity.status(200).body(customerRepository.getByUsername(username));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("id") long id) {
		
		if (!customerRepository.existsByCustomerId(id)) {
			
			return ResponseEntity.status(400).body("User not found");
		}
        return ResponseEntity.status(200).body(customerRepository.getByCustomerId(id));
        
	}

}
