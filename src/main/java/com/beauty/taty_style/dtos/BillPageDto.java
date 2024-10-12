package com.beauty.taty_style.dtos;


import java.util.List;

import lombok.Data;

@Data
public class BillPageDto {
 
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<BillDto> billDtos;
}
