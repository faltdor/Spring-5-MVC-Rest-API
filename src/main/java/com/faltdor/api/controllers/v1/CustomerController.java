package com.faltdor.api.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.api.service.impl.CustomerServiceImpl;
import com.faltdor.api.v1.model.CustomerDTO;
import com.faltdor.api.v1.model.CustomerListDTO;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private final CustomerServiceImpl customerServiceImpl;
	
	public CustomerController(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomer() {
		return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerServiceImpl.getAllCustomer()),HttpStatus.OK );
	}
	
	@GetMapping("/{firstName}")
	public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstName) {
		return new ResponseEntity<CustomerDTO>(customerServiceImpl.getCustomerByFirstName(firstName),HttpStatus.OK );
	}
	
	

}