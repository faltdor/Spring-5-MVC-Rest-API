package com.faltdor.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
	
	private Long id;
	private String firstname;
	private String lastname;
	private String customerUrl;
}
