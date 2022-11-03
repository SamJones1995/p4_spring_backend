package com.revature.repositories;

import com.revature.models.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	boolean existsByUsername(String username);

//	Optional<Customer> existsByCustomerId(long customerId);


	Optional <Customer> getByCustomerId(long customerId);
	
	Customer findByCustomerId(long customerId);

	boolean existsByCustomerId(long id);

	Customer getByUsername(String username);


	Optional<Customer> findByUsernameAndPassword(String username, String password);

}
