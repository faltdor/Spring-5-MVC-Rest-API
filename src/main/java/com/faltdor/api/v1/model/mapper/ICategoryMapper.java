package com.faltdor.api.v1.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.faltdor.api.domain.Category;
import com.faltdor.api.v1.model.CategoryDTO;

@Mapper
public interface ICategoryMapper {
	
	ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);
	
	CategoryDTO categoryToCategoryDTO(Category category);
	
	@InheritInverseConfiguration
	Category fromCategory(CategoryDTO categoryDTO);
	
}
