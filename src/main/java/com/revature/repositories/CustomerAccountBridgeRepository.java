package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Customer;
import com.revature.models.CustomerAccountBridge;


public interface CustomerAccountBridgeRepository extends JpaRepository<CustomerAccountBridge, Integer> {

	List<CustomerAccountBridge> findByCustomerId(Optional<Customer> optional);

}
