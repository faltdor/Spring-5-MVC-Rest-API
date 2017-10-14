package com.faltdor.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faltdor.api.controllers.v1.CustomerController;
import com.faltdor.api.domain.Customer;
import com.faltdor.api.repositories.ICustomerRepository;
import com.faltdor.api.service.ICustomerService;
import com.faltdor.api.service.ResourceNotFoundException;
import com.faltdor.api.v1.model.CustomerDTO;
import com.faltdor.api.v1.model.mapper.ICustomerMapper;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	private final ICustomerMapper customerMapper;
	private final ICustomerRepository customerRepository;	
	
	public CustomerServiceImpl(ICustomerRepository customerRepository,ICustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomer() {

		return customerRepository.findAll()
								 .stream()
								 .map(customer -> {
									 CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
									 customerDTO.setCustomerUrl(getCustomerUrl(customerDTO.getId()));
									 return customerDTO;
								 })
								 .collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerByFirstName(String firstName) {
		Customer returnCustomer = customerRepository.findByFirstname(firstName);
		if(returnCustomer == null) {
			throw new ResourceNotFoundException("Customer "+firstName+" Not Found");
		}
		
		return customerMapper.toCustomerDTO(returnCustomer);
	}
	
	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id)
							.map(customerMapper::toCustomerDTO)
							.orElseThrow(ResourceNotFoundException::new);
	}
	
	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDto) {

		return saveAndReturnCustomerDTO(customerDto);
	}

	private CustomerDTO saveAndReturnCustomerDTO(CustomerDTO customerDto) {
		Customer savedcustomer = customerRepository.save(customerMapper.toCustomer(customerDto));
		CustomerDTO returnDTO = customerMapper.toCustomerDTO(savedcustomer);
		returnDTO.setCustomerUrl(getCustomerUrl(savedcustomer.getId()));
		return returnDTO;
	}
	
	@Override
	public CustomerDTO updateCustomerByDto(long customerId, CustomerDTO saveCustomer) {
		saveCustomer.setId(customerId);
		
		return saveAndReturnCustomerDTO(saveCustomer);
	}
	
	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepository.findById(id).map(customer -> {
			if(customerDTO.getFirstname() != null) {
				customer.setFirstname(customerDTO.getFirstname());
			}
			if(customerDTO.getLastname() != null) {
				customer.setLastname(customerDTO.getLastname());
			}
			CustomerDTO savedDTO = customerMapper.toCustomerDTO(customerRepository.save(customer));
			savedDTO.setCustomerUrl(getCustomerUrl(savedDTO.getId()));
			
			return savedDTO;
			
		}).orElseThrow(ResourceNotFoundException::new);
		
	}
	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
	private String getCustomerUrl(Long id) {
		return CustomerController.BASE_URL +"/"+id;
	}

}
