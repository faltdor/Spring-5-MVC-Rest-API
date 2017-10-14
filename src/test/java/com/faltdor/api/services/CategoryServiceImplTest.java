package com.faltdor.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.faltdor.api.domain.Category;
import com.faltdor.api.repositories.ICategoryRepository;
import com.faltdor.api.service.impl.CategoryServiceImpl;
import com.faltdor.api.v1.model.CategoryDTO;
import com.faltdor.api.v1.model.mapper.ICategoryMapper;

import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
	
	private static final String FRUITS = "Fruits";

	@Mock
	ICategoryRepository categoryRepository;
	
	CategoryServiceImpl categoryService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(ICategoryMapper.INSTANCE,categoryRepository);
	}

	@Test
	public void testGetAllCategories() {
		Category fruits = new Category();
		fruits.setName(FRUITS);

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");
		
		List<Category> listCategoriersMock = new ArrayList<>(5);
		listCategoriersMock.add(fruits);
		listCategoriersMock.add(dried);
		listCategoriersMock.add(fresh);
		listCategoriersMock.add(exotic);
		listCategoriersMock.add(nuts);
		
		when(categoryRepository.findAll()).thenReturn(listCategoriersMock);
		
		List<CategoryDTO> categoryList = categoryService.getAllCategories();
		
		assertThat(categoryList).isNotNull();
		assertThat(categoryList).isNotEmpty();
		assertThat(categoryList.size()).isEqualTo(5);
		
	}

	@Test
	public void testGetCategoryByName() {
		//Given
		Category fruits = new Category();
		fruits.setName(FRUITS);
		
		when(categoryRepository.findByName(anyString())).thenReturn(fruits);
		
		//When
		CategoryDTO category = categoryService.getCategoryByName(FRUITS);
		
		//Then
		
		assertThat(category).isNotNull();
		assertThat(category.getName()).isNotEmpty();
		assertThat(category.getName()).isNotBlank();
		assertThat(category.getName()).isEqualTo(FRUITS);
	
	}

}
