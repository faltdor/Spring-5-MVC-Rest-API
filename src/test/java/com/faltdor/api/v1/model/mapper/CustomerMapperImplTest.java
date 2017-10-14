package com.faltdor.api.v1.model.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.api.domain.Customer;
import com.faltdor.api.v1.model.CustomerDTO;

public class CustomerMapperImplTest {
	
	private static final long _1L = 1L;
	private static final String LAST_NAME = "Newman";
	private static final String FIRST_NAME = "Joe";
	
	ICustomerMapper customerMapper;
	
	
	@Before
	public void setUp() throws Exception {
		
		customerMapper = ICustomerMapper.INSTANCE;
	}

	@Test
	public void testToCustomerDTO() {
		Customer customer = new Customer();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(_1L);
		
		CustomerDTO customerDto = customerMapper.toCustomerDTO(customer);
		
		assertThat(customerDto).isNotNull();
		assertThat(customerDto.getFirstname()).isEqualTo(FIRST_NAME);
		assertThat(customerDto.getLastname()).isEqualTo(LAST_NAME);
		assertThat(customerDto.getId()).isEqualTo(_1L);
		
		
	}
	
	
	@Test
	public void testToCustomer() {
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstname(FIRST_NAME);
		customerDto.setLastname(LAST_NAME);
		customerDto.setId(_1L);
		
		Customer customer = customerMapper.toCustomer(customerDto);
		
		assertThat(customer).isNotNull();
		assertThat(customer.getFirstname()).isEqualTo(FIRST_NAME);
		assertThat(customer.getLastname()).isEqualTo(LAST_NAME);
		assertThat(customer.getId()).isEqualTo(_1L);
	}
	
	
	

}
