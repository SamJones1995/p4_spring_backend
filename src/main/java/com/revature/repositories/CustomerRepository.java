package com.revature.repositories;

import com.revature.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	boolean existsByUsername(String username);

}
