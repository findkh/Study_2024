package com.kh.ex3.product.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.kh.ex3.product.entity.ProductEntity;
import com.kh.ex3.product.entity.ProductImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private Long pno;
	
	private String pname;
	
	private int price;
	
	private String content;
	
	private String writer;
	
	private List<String> imageList;
	
	public ProductDTO(ProductEntity productEntity) {
		this.pno = productEntity.getPno();
		this.pname = productEntity.getPname();
		this.price = productEntity.getPrice();
		this.content = productEntity.getContent();
		this.writer = productEntity.getWriter();
		this.imageList = productEntity.getImages().stream().map(ProductImage::getFileName).collect(Collectors.toList());
	}
}
