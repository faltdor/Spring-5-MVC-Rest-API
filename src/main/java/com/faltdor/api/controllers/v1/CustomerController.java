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

import com.faltdor.api.service.impl.CustomerServiceImpl;
import com.faltdor.api.v1.model.CustomerDTO;
import com.faltdor.api.v1.model.CustomerListDTO;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";
	
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
	
	@GetMapping("/id/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		return new ResponseEntity<CustomerDTO>(customerServiceImpl.getCustomerById(id),HttpStatus.OK );
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDto){
		
		return new ResponseEntity<CustomerDTO>(customerServiceImpl.createNewCustomer(customerDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateNewCustomer(@RequestBody CustomerDTO customerDto,@PathVariable String id){
		
		return new ResponseEntity<CustomerDTO>(customerServiceImpl.updateCustomerByDto(Long.valueOf(id),customerDto),HttpStatus.CREATED);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDto,@PathVariable String id){
		
		return new ResponseEntity<CustomerDTO>(customerServiceImpl.patchCustomer(Long.valueOf(id),customerDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
		customerServiceImpl.deleteCustomer(Long.valueOf(id));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

}
