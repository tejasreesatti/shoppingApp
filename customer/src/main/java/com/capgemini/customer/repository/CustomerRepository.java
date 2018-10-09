package com.capgemini.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
