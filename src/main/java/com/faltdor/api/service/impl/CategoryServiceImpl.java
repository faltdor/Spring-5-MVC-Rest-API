package com.faltdor.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faltdor.api.repositories.ICategoryRepository;
import com.faltdor.api.service.ICategoryService;
import com.faltdor.api.v1.model.CategoryDTO;
import com.faltdor.api.v1.model.mapper.ICategoryMapper;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	private final ICategoryMapper categoryMapper; 
	private final ICategoryRepository categoryRepository;
	

	public CategoryServiceImpl(ICategoryMapper instance, ICategoryRepository categoryRepository) {
		this.categoryMapper = instance;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAll()
								 .stream()
								 .map(categoryMapper::categoryToCategoryDTO)
								 .collect(Collectors.toList());
						
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {

		return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
	}

}
