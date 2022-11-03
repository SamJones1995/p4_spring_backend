package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.ApproveRequest;
import com.revature.dtos.DeleteUserRequest;
import com.revature.dtos.LoginRequest;
import com.revature.models.Account;
import com.revature.models.Admin;
import com.revature.models.Customer;
import com.revature.repositories.AdminRepository;
import com.revature.services.AccountService;
import com.revature.services.CustomerService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	private final AdminRepository adminRepository;
	private final AccountService accountService;
	private final CustomerService customerService;
	
	public AdminController(AdminRepository adminRepository, AccountService accountService, CustomerService customerService) {
		this.adminRepository = adminRepository;
		this.accountService = accountService;
		this.customerService = customerService;
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Admin> optional = adminRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.status(400).body("Login failed");
        }

        return ResponseEntity.ok(optional.get());
    }
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/allcustomers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@PutMapping("/customerupdate")
	public Customer updateCustomer(@RequestBody Customer updatedCustomer) {
		
		return customerService.updateCustomer(updatedCustomer.getCustomerId(), updatedCustomer);
	}
	
	@PutMapping("/approve")
	public Account approve(@RequestBody ApproveRequest approveRequest) {
		Account account = accountService.findByAccountId(Long.valueOf(approveRequest.getAccountId()));
		return accountService.approve(account);
	}
	
	@DeleteMapping("/delete")
	public void deleteCustomer(@RequestBody DeleteUserRequest deleteUserRequest) {
		customerService.deleteCustomer(Long.valueOf(deleteUserRequest.getCustomerId()));
	}
}
