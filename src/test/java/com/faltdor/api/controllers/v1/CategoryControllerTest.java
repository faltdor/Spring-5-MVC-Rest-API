package com.faltdor.api.controllers.v1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.faltdor.api.service.ResourceNotFoundException;
import com.faltdor.api.service.impl.CategoryServiceImpl;
import com.faltdor.api.v1.model.CategoryDTO;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CategoryControllerTest {
	
	private static final String FRUITS = "fruits";

	@Mock
	CategoryServiceImpl categoryServiceImpl;
	
	@InjectMocks
	CategoryController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
	}

	@Test
	public void testListCategories() throws Exception {
		 CategoryDTO category1 = new CategoryDTO();
	        category1.setId(1l);
	        category1.setName(FRUITS);

	        CategoryDTO category2 = new CategoryDTO();
	        category2.setId(2l);
	        category2.setName("Bob");

	        List<CategoryDTO> categories = Arrays.asList(category1, category2);

	        when(categoryServiceImpl.getAllCategories()).thenReturn(categories);

	        mockMvc.perform(get(CategoryController.BASE_URL)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
//	                .andExpect(jsonPath("$.categories", hasSize(2)));
		
	}
	
	@Test
	public void testGetByNameCategories() throws Exception {
		//Given	
		CategoryDTO fruits = new CategoryDTO();
		fruits.setName(FRUITS);
		fruits.setId(1L);
		
		when(categoryServiceImpl.getCategoryByName(anyString())).thenReturn(fruits);
		
		//when
		mockMvc.perform(get(CategoryController.BASE_URL+"/fruits")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name",equalTo(FRUITS)));
	}
	
	@Test
	public void testGetByNameCategoriesNotFound() throws Exception {
		
		when(categoryServiceImpl.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);
		
		//when
		mockMvc.perform(get(CategoryController.BASE_URL+"/fruits")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
