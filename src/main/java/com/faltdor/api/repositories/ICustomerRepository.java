package com.faltdor.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faltdor.api.domain.Customer;


public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByFirstname(String name);

}
