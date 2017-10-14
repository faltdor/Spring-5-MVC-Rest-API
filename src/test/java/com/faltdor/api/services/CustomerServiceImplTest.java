package com.faltdor.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.faltdor.api.domain.Customer;
import com.faltdor.api.repositories.ICustomerRepository;
import com.faltdor.api.service.impl.CustomerServiceImpl;
import com.faltdor.api.v1.model.CustomerDTO;
import com.faltdor.api.v1.model.mapper.ICustomerMapper;

public class CustomerServiceImplTest {

	private static final long _1L = 1L;
	private static final String LAST_NAME = "Newman";
	private static final String FIRST_NAME = "Joe";

	@Mock
	ICustomerRepository customerRepository;
	
	CustomerServiceImpl customerServiceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerServiceImpl = new CustomerServiceImpl(customerRepository, ICustomerMapper.INSTANCE);
	}

	@Test
	public void testGetAllCustomer() {
		// Given
		Customer customer = new Customer();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(_1L);

		Customer customer2 = new Customer();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(2L);

		List<Customer> customers = Arrays.asList(customer, customer2);

		when(customerRepository.findAll()).thenReturn(customers);

		// When
		List<CustomerDTO> customerList = customerServiceImpl.getAllCustomer();

		assertThat(customerList).isNotNull();
		assertThat(customerList).isNotEmpty();
		assertThat(customerList.size()).isEqualTo(2);

	}

	@Test
	public void testGetCustomerByName() {
		// Given
		Customer customer = new Customer();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(_1L);

		when(customerRepository.findByFirstname(anyString())).thenReturn(customer);

		// When
		CustomerDTO customeDto = customerServiceImpl.getCustomerByFirstName(FIRST_NAME);

		assertThat(customeDto).isNotNull();
		assertThat(customeDto.getFirstname()).isNotEmpty();
		assertThat(customeDto.getFirstname()).isNotBlank();
		assertThat(customeDto.getFirstname()).isEqualTo(FIRST_NAME);
		assertThat(customeDto.getLastname()).isEqualTo(LAST_NAME);
		assertThat(customeDto.getLastname()).isNotEmpty();
		assertThat(customeDto.getLastname()).isNotBlank();
		assertThat(customeDto.getId()).isEqualTo(_1L);

	}
	
	
	@Test
	public void testCreateNewCustomer() {
		// Given
		Customer customer = new Customer();
		String firstName = "Adan";
		customer.setFirstname(firstName);
		String lastname = "Smitt";
		customer.setLastname(lastname);
		customer.setId(_1L);

		when(customerRepository.save(any(Customer.class))).thenReturn(customer);
		
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstname(firstName);
		customerDto.setLastname(lastname);
		
		// When
		CustomerDTO customeDto = customerServiceImpl.createNewCustomer(customerDto);

		assertThat(customeDto).isNotNull();
		assertThat(customeDto.getFirstname()).isNotEmpty();
		assertThat(customeDto.getFirstname()).isNotBlank();
		assertThat(customeDto.getFirstname()).isEqualTo(firstName);
		assertThat(customeDto.getLastname()).isEqualTo(lastname);
		assertThat(customeDto.getLastname()).isNotEmpty();
		assertThat(customeDto.getLastname()).isNotBlank();
		assertThat(customeDto.getId()).isEqualTo(_1L);

	}
	
	@Test
	public void testSaveCustomerByDto() {
		// Given
		String firstName = "Adan";
		String lastname = "Smitt";
				
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstname(firstName);
		customerDto.setLastname(lastname);
		
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstname(customerDto.getFirstname());
		savedCustomer.setLastname(customerDto.getLastname());
		savedCustomer.setId(_1L);

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		// When
		CustomerDTO savedDto = customerServiceImpl.updateCustomerByDto(_1L,customerDto);

		assertThat(savedDto).isNotNull();
		assertThat(savedDto.getFirstname()).isNotEmpty();
		assertThat(savedDto.getFirstname()).isNotBlank();
		assertThat(savedDto.getFirstname()).isEqualTo(firstName);
		assertThat(savedDto.getLastname()).isEqualTo(lastname);
		assertThat(savedDto.getLastname()).isNotEmpty();
		assertThat(savedDto.getLastname()).isNotBlank();
		assertThat(savedDto.getId()).isEqualTo(_1L);

	}

}
