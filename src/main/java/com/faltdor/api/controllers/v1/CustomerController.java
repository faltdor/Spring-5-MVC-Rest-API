package com.faltdor.api.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.faltdor.api.service.impl.CustomerServiceImpl;
import com.faltdor.api.v1.model.CustomerDTO;
import com.faltdor.api.v1.model.CustomerListDTO;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";
	
	private final CustomerServiceImpl customerServiceImpl;
	
	public CustomerController(CustomerServiceImpl customerServiceImpl) {
		this.customerServiceImpl = customerServiceImpl;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getAllCustomer() {
		return  new CustomerListDTO(customerServiceImpl.getAllCustomer());
	}
	
	@GetMapping("/{firstName}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerByFirstName(@PathVariable String firstName) {
		return  customerServiceImpl.getCustomerByFirstName(firstName);
	}
	
	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerById(@PathVariable Long id) {
		return  customerServiceImpl.getCustomerById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDto){
		
		return  customerServiceImpl.createNewCustomer(customerDto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO updateNewCustomer(@RequestBody CustomerDTO customerDto,@PathVariable String id){
		
		return  customerServiceImpl.updateCustomerByDto(Long.valueOf(id),customerDto);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO patchCustomer(@RequestBody CustomerDTO customerDto,@PathVariable String id){
		
		return  customerServiceImpl.patchCustomer(Long.valueOf(id),customerDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable String id){
		customerServiceImpl.deleteCustomer(Long.valueOf(id));
	}
	
	

}
