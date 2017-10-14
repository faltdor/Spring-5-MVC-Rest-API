package com.faltdor.api.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.api.service.impl.CategoryServiceImpl;
import com.faltdor.api.v1.model.CategoryDTO;
import com.faltdor.api.v1.model.CategoryListDTO;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	private final CategoryServiceImpl categoryServiceImpl;

	public CategoryController(CategoryServiceImpl categoryServiceImpl) {
		this.categoryServiceImpl = categoryServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<CategoryListDTO> getallCategories(){
		
		return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryServiceImpl.getAllCategories()), HttpStatus.OK);
	}
	
	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
		
		return new ResponseEntity<CategoryDTO>(categoryServiceImpl.getCategoryByName(name),HttpStatus.OK);
	}
	
	
}
