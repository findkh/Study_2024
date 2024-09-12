package com.kh.ex3.product.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
	@Min(1)
	@Builder.Default
	private int page = 1;
	
	@Min(10)
	@Max(100)
	@Builder.Default
	private int size = 10;
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page - 1, size, sort);
	}
}
