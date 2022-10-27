package com.revature.models;

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

}
