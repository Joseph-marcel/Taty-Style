package com.beauty.taty_style.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	
	private Long customerId;
	private String firstName;
	private String phoneNumber;

}
