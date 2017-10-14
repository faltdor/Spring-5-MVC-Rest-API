package com.faltdor.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.faltdor.api.bootstrap.Bootstrap;
import com.faltdor.api.domain.Customer;
import com.faltdor.api.repositories.ICategoryRepository;
import com.faltdor.api.repositories.ICustomerRepository;
import com.faltdor.api.service.impl.CustomerServiceImpl;
import com.faltdor.api.v1.model.CustomerDTO;
import com.faltdor.api.v1.model.mapper.ICustomerMapper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {
	
	@Autowired
	ICustomerRepository customerRepository;

	@Autowired
	ICategoryRepository categoryRepository;

	CustomerServiceImpl customerServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Loading Customer data for test");
		System.out.println(customerRepository.findAll().size());
		
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
		bootstrap.run();
		
		customerServiceImpl = new CustomerServiceImpl(customerRepository, ICustomerMapper.INSTANCE);
	}

	@Test
	public void patchCustomerUpdateFirtsName() {
		String updateName = "update name";
		Long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertThat(originalCustomer).isNotNull();
		
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstname(updateName);
		
		customerServiceImpl.patchCustomer(id,customerDTO);
		
		Customer updateCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updateCustomer);
		assertThat(updateName).isEqualTo(updateCustomer.getFirstname());
		assertThat(originalFirstName).isNotEqualTo(updateCustomer.getFirstname());
		assertThat(originalLastName).isEqualTo(updateCustomer.getLastname());
	}
	
	
	@Test
	public void patchCustomerUpdateLastName() {
		String updateName = "update name";
		Long id = getCustomerIdValue();
		
		Customer originalCustomer = customerRepository.getOne(id);
		assertThat(originalCustomer).isNotNull();
		
		String originalFirstName = originalCustomer.getFirstname();
		String originalLastName = originalCustomer.getLastname();
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastname(updateName);
		
		customerServiceImpl.patchCustomer(id,customerDTO);
		
		Customer updateCustomer = customerRepository.findById(id).get();
		
		assertNotNull(updateCustomer);
		assertThat(updateName).isEqualTo(updateCustomer.getLastname());
		assertThat(originalFirstName).isEqualTo(updateCustomer.getFirstname());
		assertThat(originalLastName).isNotEqualTo(updateCustomer.getLastname());
	}
	
	private Long getCustomerIdValue() {
		 List<Customer> customerlist = customerRepository.findAll();
		 System.out.println("Customers Found: "+ customerlist.size());
		 
		 return customerlist.get(0).getId();
	}
	
	
	
}
