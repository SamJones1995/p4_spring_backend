package com.revature.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.TransferRequest;
import com.revature.dtos.WithDepRequest;
import com.revature.models.Account;
import com.revature.models.CustomerAccountBridge;
import com.revature.repositories.AccountRepository;
import com.revature.repositories.CustomerAccountBridgeRepository;
import com.revature.repositories.CustomerRepository;
import com.revature.services.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
	
	private final AccountService accountService;
	private final CustomerRepository customerRepository;
	private CustomerAccountBridgeRepository customerAccountBridgeRepository;
	private AccountRepository accountRepository;
	
	public AccountController(AccountService accountService, CustomerRepository customerRepository, CustomerAccountBridgeRepository customerAccountBridgeRepository,
			AccountRepository accountRepository) {
		this.accountService = accountService;
		this.customerRepository = customerRepository;
		this.customerAccountBridgeRepository = customerAccountBridgeRepository;
		this.accountRepository = accountRepository;
	}
	
	@GetMapping("/{id}")
	public List<Account> getAccountsByCustomer(@PathVariable("id") long customerId) {

		
		return accountService.getAllAccountsByCustomerId(customerId);
	}
	
	@PostMapping("/{id}")
	public Account addNewAccount(@PathVariable("id") long customerId, @RequestBody Account newAccount) {
		

		accountRepository.save(newAccount);
		
		CustomerAccountBridge newBridge = new CustomerAccountBridge();
		
		newBridge.setAccountId(newAccount);
		
		newBridge.setCustomerId(customerRepository.getByCustomerId(customerId));
			
		return accountService.createAccount(customerId, newAccount, newBridge);
		
	}
	
	
	@PutMapping("/withdraw")
	public Account withdraw(@RequestBody WithDepRequest withRequest) {
		
		return accountService.withdraw(accountRepository.findByAccountId(Long.valueOf(withRequest.getAccountId())), Long.valueOf(withRequest.getAmount()));
		
	}
	
	@PutMapping("/deposit")
	public Account deposit(@RequestBody WithDepRequest withRequest) {
		
		return accountService.deposit(accountRepository.findByAccountId(Long.valueOf(withRequest.getAccountId())), Long.valueOf(withRequest.getAmount()));
		
	}
	
	@PutMapping("/transfer")
	public Account transferFunds(@RequestBody TransferRequest transferRequest) {
		
		return accountService.transferFunds(accountRepository.findByAccountId(Long.valueOf(transferRequest.getAccountId1())), accountRepository.findByAccountId(Long.valueOf(transferRequest.getAccountId2())), Long.valueOf(transferRequest.getAmount()));
		
	}
	
	
	

}
