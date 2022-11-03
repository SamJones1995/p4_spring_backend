package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Account;
import com.revature.models.Admin;
import com.revature.models.Customer;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUsernameAndPassword(String username, String password);

}
