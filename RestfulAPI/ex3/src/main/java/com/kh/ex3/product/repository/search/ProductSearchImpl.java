package com.kh.ex3.product.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.kh.ex3.product.dto.ProductDTO;
import com.kh.ex3.product.dto.ProductListDTO;
import com.kh.ex3.product.entity.ProductEntity;
import com.kh.ex3.product.entity.QProductEntity;
import com.kh.ex3.product.entity.QProductImage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

	public ProductSearchImpl() {
		super(ProductEntity.class);
	}

	@Override
	public Page<ProductListDTO> list(Pageable pageable) {
		
		QProductEntity productEntity = QProductEntity.productEntity;
		QProductImage productImage = QProductImage.productImage;
		
		JPQLQuery<ProductEntity> query = from(productEntity);
		query.leftJoin(productEntity.images, productImage);
		
		query.where(productImage.idx.eq(0));
		
		// DTO로 프로젝션
		JPQLQuery<ProductListDTO> dtojpqlQuery = query.select(Projections.bean(ProductListDTO.class, 
			productEntity.pno,
			productEntity.pname, 
			productEntity.price, 
			productEntity.writer, 
			productImage.fileName.as("productImage")));
			
		// 페이징 적용
		JPQLQuery<ProductListDTO> paginatedQuery = getQuerydsl().applyPagination(pageable, dtojpqlQuery);
		
		// 결과 리스트 및 카운트 조회
		List<ProductListDTO> dtoList = paginatedQuery.fetch();
		long count = paginatedQuery.fetchCount();
		
		// 결과 반환
		return new PageImpl<>(dtoList, pageable, count);
	}

	@Override
	public Page<ProductDTO> listWithAllImages(Pageable pageable) {
		QProductEntity productEntity = QProductEntity.productEntity;
		JPQLQuery<ProductEntity> query = from(productEntity);
		this.getQuerydsl().applyPagination(pageable, query);
		List<ProductEntity> entityList = query.fetch();
		long count = query.fetchCount();
		
//		for(ProductEntity entity : entityList) {
//			System.out.println(entity);
//			System.out.println(entity.getImages());
//			System.out.println("====================");
//		}
		List<ProductDTO> dtoList = entityList.stream().map(ProductDTO::new).toList();
		
		return new PageImpl<>(dtoList, pageable, count);
	}

	@Override
	public Page<ProductDTO> listFetchAllImages(Pageable pageable) {
		QProductEntity productEntity = QProductEntity.productEntity;
		QProductImage productImage = QProductImage.productImage;
		
		JPQLQuery<ProductEntity> query = from(productEntity);
		query.leftJoin(productEntity.images, productImage).fetchJoin();
		
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<ProductEntity> entityList = query.fetch();
		
//		long count = query.fetchCount();
//		
//		for (ProductEntity entity: entityList) {
//			System.out.println(entity);
//			System.out.println(entity.getImages());
//			System.out.println("===================");
//		}
		
		List<ProductDTO> dtoList = entityList.stream().map(ProductDTO::new).toList();
		long count = query.fetchCount();
		return new PageImpl<>(dtoList, pageable, count);
	}

}
