package com.faltdor.api.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.faltdor.api.service.impl.CategoryServiceImpl;
import com.faltdor.api.v1.model.CategoryDTO;
import com.faltdor.api.v1.model.CategoryListDTO;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

	public static final String BASE_URL = "/api/v1/categories";
	
	private final CategoryServiceImpl categoryServiceImpl;

	public CategoryController(CategoryServiceImpl categoryServiceImpl) {
		this.categoryServiceImpl = categoryServiceImpl;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO getallCategories(){
		return new CategoryListDTO(categoryServiceImpl.getAllCategories());
	}
	
	@GetMapping("/{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getCategoryByName(@PathVariable String name){
		return categoryServiceImpl.getCategoryByName(name);
	}
	
	
	
	
	
}
