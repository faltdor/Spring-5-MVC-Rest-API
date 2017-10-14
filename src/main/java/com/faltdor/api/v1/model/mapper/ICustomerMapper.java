package com.faltdor.api.v1.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.faltdor.api.domain.Customer;
import com.faltdor.api.v1.model.CustomerDTO;

@Mapper
public interface ICustomerMapper {
	
	ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);
	
	CustomerDTO toCustomerDTO(Customer customer);
	
	@InheritInverseConfiguration
	Customer toCustomer(CustomerDTO customerDto);
}
