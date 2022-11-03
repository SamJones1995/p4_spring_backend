package com.revature.models;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_account_bridge")
public class CustomerAccountBridge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerAccountBridgeId;
	
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customerId;
	
	@OneToOne
	@JoinColumn(name = "accountId")
	private Account accountId;

	public long getCustomerAccountBridgeId() {
		return customerAccountBridgeId;
	}

	public void setCustomerAccountBridgeId(long customerAccountBridgeId) {
		this.customerAccountBridgeId = customerAccountBridgeId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public void setCustomerId(Optional<Customer> byCustomerId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
