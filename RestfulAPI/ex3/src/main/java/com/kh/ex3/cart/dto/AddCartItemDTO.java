package com.kh.ex3.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemDTO {
	private String holder;
	
	private Long pno;
	
	private int quantity;
}
