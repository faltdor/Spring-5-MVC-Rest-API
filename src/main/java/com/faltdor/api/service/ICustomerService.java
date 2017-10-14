package com.faltdor.api.service;

import java.util.List;

import com.faltdor.api.v1.model.CustomerDTO;


public interface ICustomerService {
	
	List<CustomerDTO> getAllCustomer();
	
	CustomerDTO getCustomerByFirstName(String firstName);

	CustomerDTO getCustomerById(Long id);

	CustomerDTO createNewCustomer(CustomerDTO customerDto);

	CustomerDTO updateCustomerByDto(long customerId, CustomerDTO saveCustomer);

	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

	void deleteCustomer(Long id);
}
