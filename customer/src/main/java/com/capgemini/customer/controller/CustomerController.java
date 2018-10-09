package com.capgemini.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exceptions.AuthenticationFailedException;
import com.capgemini.customer.exceptions.CustomerNotFoundException;
import com.capgemini.customer.service.CustomerService;


@RestController
public class CustomerController {
	 
	private  final Logger log = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	 private CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer (@RequestBody Customer customer){
		
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
		log.info("Customer Added");
		return responseEntity;
		
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer (@RequestBody Customer customer) throws CustomerNotFoundException{
		
		Customer customer1 = customerService.findCustomerById(customer.getCustomerId());
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> findCustomerById (@PathVariable int customerId){
		
		Customer customer1 = customerService.findCustomerById(customerId);
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.findCustomerById(customerId), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	

	}
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId){

		Customer customer1 = customerService.findCustomerById(customerId);
		if (customer1 != null) {
			customerService.deleteCustomer(customer1);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
		
	@PostMapping("/login")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) throws AuthenticationFailedException{
		
		Customer customer1 = customerService.authenticate(customer);
		if (customer1 != null)
			return new ResponseEntity<Customer>(customerService.authenticate(customer), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List> listAllCustomers(){
		List<Customer> list = customerService.getAllCustomers();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

}

