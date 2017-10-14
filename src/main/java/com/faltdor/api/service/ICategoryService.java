package com.faltdor.api.service;

import java.util.List;

import com.faltdor.api.v1.model.CategoryDTO;

public interface ICategoryService {
	
	List<CategoryDTO> getAllCategories();
	
	CategoryDTO getCategoryByName(String name);
	
}
