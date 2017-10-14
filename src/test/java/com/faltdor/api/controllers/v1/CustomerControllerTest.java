package com.faltdor.api.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.api.service.impl.CustomerServiceImpl;
import com.faltdor.api.v1.model.CustomerDTO;

public class CustomerControllerTest {

	private static final long _1L = 1L;
	private static final String LAST_NAME = "Newman";
	private static final String FIRST_NAME = "Joe";

	@Mock
	CustomerServiceImpl customerServiceImpl;

	CustomerController customerController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		customerController = new CustomerController(customerServiceImpl);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		// Given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(_1L);

		CustomerDTO customer2 = new CustomerDTO();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(2L);

		List<CustomerDTO> customers = Arrays.asList(customer, customer2);

		// When
		when(customerServiceImpl.getAllCustomer()).thenReturn(customers);
		
		mockMvc.perform(get("/api/v1/customers")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());

	}
	
	
	@Test
	public void testGetCustomerByFirstName() throws Exception {
		// Given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		customer.setId(_1L);

		// When
		when(customerServiceImpl.getCustomerByFirstName(anyString())).thenReturn(customer);
		
		mockMvc.perform(get("/api/v1/customers/Joe")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.firstname",equalTo(FIRST_NAME)));

	}

}
