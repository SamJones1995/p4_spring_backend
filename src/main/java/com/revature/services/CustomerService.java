package com.revature.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List <Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Optional <Customer> getCustomerById(Long customerId) 
		throws ResourceNotFoundException {
			return customerRepository.getByCustomerId(customerId);
		}
	
	public Customer createCustomer (Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer updateCustomer(Long customerId, Customer customerDetails) throws ResourceNotFoundException {
		Customer customer = customerRepository.getByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + customerId));
		if (customerDetails.getUsername() != null)
		customer.setUsername(customerDetails.getUsername());
		if (customerDetails.getPassword() != null)
		customer.setPassword(customerDetails.getPassword());
		if (customerDetails.getFirstName() != null)
		customer.setFirstName(customerDetails.getFirstName());
		if (customerDetails.getLastName() != null)
		customer.setLastName(customerDetails.getLastName());
		if (customerDetails.getAddress() != null)
		customer.setAddress(customerDetails.getAddress());
		if (customerDetails.getAddress2() != null)
		customer.setAddress2(customerDetails.getAddress2());
		if (customerDetails.getCity() != null)
		customer.setCity(customerDetails.getCity());
		if (customerDetails.getState() != null)
		customer.setState(customerDetails.getState());
		if (customerDetails.getZip() != 0)
		customer.setZip(customerDetails.getZip());
		final Customer updatedCustomer = customerRepository.save(customer);
		return updatedCustomer;

	}
	
	public Map < String, Boolean > deleteCustomer(Long customerId) 
	throws ResourceNotFoundException {
		Customer customer = customerRepository.getByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + customerId));
		
		customerRepository.delete(customer);
		Map < String, Boolean > response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	

}
