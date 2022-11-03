package com.revature.services;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.CustomerAccountBridge;
import com.revature.repositories.AccountRepository;
import com.revature.repositories.CustomerAccountBridgeRepository;
import com.revature.repositories.CustomerRepository;

@Service
public class AccountService {
	
	@Autowired
	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	private CustomerAccountBridgeRepository customerAccountBridgeRepository;
	
	public AccountService(CustomerAccountBridgeRepository customerAccountBridgeRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.customerAccountBridgeRepository = customerAccountBridgeRepository;
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}
	
	public List<Account> getAllAccountsByCustomerId(long customerId) {
		List<CustomerAccountBridge> accountBridges = customerAccountBridgeRepository.findByCustomerId(customerRepository.getByCustomerId(customerId));
		
		List<Long> accounts = new LinkedList<Long>();
		
		ListIterator<Long> accountIds = accounts.listIterator();
		
		for (CustomerAccountBridge i: accountBridges) {
			accountIds.add(i.getAccountId().getAccountId());
		
		}
		
		List<Account> accountsResult = new LinkedList<Account>();
		
		
		ListIterator<Account> accountsIt = accountsResult.listIterator();
		
		for (long i: accounts) {
			accountsIt.add(accountRepository.findByAccountId(i));
			
		}
		
		return accountsResult;
	}
	
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
	
	public Account findByAccountId(long accountId) {
		return accountRepository.findByAccountId(accountId);
	}
	
	public Account createAccount(long customerId, Account newAccount, CustomerAccountBridge newBridge) {
		
		
		newBridge.setCustomerId(customerRepository.findByCustomerId(customerId));
		
		customerAccountBridgeRepository.save(newBridge);
		
		return newAccount;
	}
	
	public Account withdraw(Account account, long amount) {
		
		long newAmount = account.getBalance().longValue() - amount;
		
		account.setBalance(BigDecimal.valueOf(newAmount));
		
		return accountRepository.save(account);
	}
	
	public Account deposit(Account account, long amount) {
		
		long newAmount = account.getBalance().longValue() + amount;
		
		account.setBalance(BigDecimal.valueOf(newAmount));
		
		return accountRepository.save(account);
	}
	
	public Account approve(Account account) {
		account.setApproved(true);
		return accountRepository.save(account);
	}
	
	public Account transferFunds(Account account1, Account account2, long amount) {
		
		long newAmount = account1.getBalance().longValue() - amount;
		
		account1.setBalance(BigDecimal.valueOf(newAmount));
		
		accountRepository.save(account1);
		
		
		
		long newAmount2 = account2.getBalance().longValue() + amount;
		
		account2.setBalance(BigDecimal.valueOf(newAmount2));
		
		accountRepository.save(account2);
		
		return account2;
		
		
	}

}
