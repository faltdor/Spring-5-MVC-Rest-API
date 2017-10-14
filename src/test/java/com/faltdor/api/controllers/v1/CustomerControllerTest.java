package com.faltdor.api.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

public class CustomerControllerTest extends AbstractRestControllerTest{

	private static final String ANY_NAME = "Any Name";
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
		
		mockMvc.perform(get(CustomerController.BASE_URL)
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
		
		mockMvc.perform(get(CustomerController.BASE_URL+"/Joe")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.firstname",equalTo(FIRST_NAME)));

	}
	
	@Test
	public void testCreateNewCustomer() throws Exception {
		// Given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstname(FIRST_NAME);
		customer.setLastname(LAST_NAME);
		
		
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstname(customer.getFirstname());
		customerDto.setLastname(customer.getLastname());

		// When
		when(customerServiceImpl.createNewCustomer(any(CustomerDTO.class))).thenReturn(customerDto);
		
		mockMvc.perform(post(CustomerController.BASE_URL)
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(customer)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.firstname",equalTo(FIRST_NAME)));

	}
	
	
	@Test
	public void testUpdateCustomer() throws Exception {
		// Given
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstname(FIRST_NAME);
		customerDto.setLastname(LAST_NAME);
		customerDto.setCustomerUrl(CustomerController.BASE_URL+"/1");
		// When
		when(customerServiceImpl.updateCustomerByDto(anyLong(),any(CustomerDTO.class))).thenReturn(customerDto);
		
		mockMvc.perform(put(CustomerController.BASE_URL+"/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(customerDto)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.firstname",equalTo(FIRST_NAME)))
						.andExpect(jsonPath("$.lastname",equalTo(LAST_NAME)))
						.andExpect(jsonPath("$.customerUrl",equalTo(CustomerController.BASE_URL+"/1")));

	}
	
	
	@Test
	public void testPatchCustomer() throws Exception {
		// Given
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setFirstname(ANY_NAME);
		
		
		CustomerDTO returncustomerDto = new CustomerDTO();
		returncustomerDto.setFirstname(customerDto.getFirstname());
		returncustomerDto.setLastname(LAST_NAME);
		returncustomerDto.setCustomerUrl(CustomerController.BASE_URL+"/1");
		
		// When
		when(customerServiceImpl.patchCustomer(anyLong(),any(CustomerDTO.class))).thenReturn(returncustomerDto);
		
		mockMvc.perform(patch(CustomerController.BASE_URL+"/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(customerDto)))
						.andExpect(status().isCreated())
						.andExpect(jsonPath("$.firstname",equalTo(ANY_NAME)))
						.andExpect(jsonPath("$.lastname",equalTo(LAST_NAME)))
						.andExpect(jsonPath("$.customerUrl",equalTo(CustomerController.BASE_URL+"/1")));

	}
	
	@Test
	public void testDeleteCustomer() throws Exception {
		// Given
		Long id = _1L;
		// When
		
		mockMvc.perform(delete(CustomerController.BASE_URL+"/1")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
		
		verify(customerServiceImpl,times(1)).deleteCustomer(anyLong());;

	}
	
	
	

}
