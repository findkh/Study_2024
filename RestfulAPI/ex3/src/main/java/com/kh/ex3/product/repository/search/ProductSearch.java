package com.kh.ex3.product.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kh.ex3.product.dto.ProductDTO;
import com.kh.ex3.product.dto.ProductListDTO;

public interface ProductSearch {
	Page<ProductListDTO> list(Pageable pageable);
	
	Page<ProductDTO> listWithAllImages(Pageable pageable);
	
	//페치 조인
	Page<ProductDTO> listFetchAllImages(Pageable pageable);
	
	Page<ProductListDTO> listWithReviewCount(Pageable pageable);
	
	Page<ProductDTO> listWIthAllImagesReviewCount(Pageable pageable);
}
