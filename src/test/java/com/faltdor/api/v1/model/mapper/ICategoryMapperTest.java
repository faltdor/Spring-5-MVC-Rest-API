package com.faltdor.api.v1.model.mapper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.api.domain.Category;
import com.faltdor.api.v1.model.CategoryDTO;

public class ICategoryMapperTest {
	
	private static final String NAME = "Fruits";
	private static final Long ID = 1L;
	
//	ICategoryMapper categoryMapper = ICategoryMapper.INSTANCE; 
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCategoryToCategoryDTO() {
		Category fruits = new Category();
		fruits.setName(NAME);
		fruits.setId(ID);
		
		//CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(fruits);
		CategoryDTO categoryDTO =ICategoryMapper.INSTANCE.categoryToCategoryDTO(fruits);
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}

}
