package com.kh.ex3.product.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.kh.ex3.product.entity.ProductEntity;
import com.kh.ex3.product.entity.ProductImage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private Long pno;
	
	@NotEmpty
	private String pname;
	
	@Min(0)
	private int price;
	
	private String content;
	
	@NotEmpty
	private String writer;
	
	private List<String> imageList;
	
	private long reviewCount;
	
	public ProductDTO(ProductEntity productEntity) {
		this.pno = productEntity.getPno();
		this.pname = productEntity.getPname();
		this.price = productEntity.getPrice();
		this.content = productEntity.getContent();
		this.writer = productEntity.getWriter();
		this.imageList = productEntity.getImages().stream().map(ProductImage::getFileName).collect(Collectors.toList());
	}
	
	public ProductEntity toEntity() {
		ProductEntity productEntity = ProductEntity.builder()
				.pno(pno)
				.pname(pname)
				.price(price)
				.content(content)
				.writer(writer)
				.build();
		
		if(imageList == null || imageList.isEmpty()) {
			return productEntity;
		}
		
		imageList.forEach(productEntity::addImage);
		return productEntity;
	}
}
