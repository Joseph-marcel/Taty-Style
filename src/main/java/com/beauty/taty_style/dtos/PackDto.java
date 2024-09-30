package com.beauty.taty_style.dtos;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackDto {
	
	private Long packId;
	private List<AllowanceDto> allowancesDto;

}
