package com.kh.ex3.product.repository;



import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex3.product.dto.ProductDTO;
import com.kh.ex3.product.dto.ProductListDTO;
import com.kh.ex3.product.entity.ProductEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	@Test
	public void testListWithALlImagesReviewCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("pno"));
		Page<ProductDTO> result = productRepository.listWIthAllImagesReviewCount(pageable);
		result.getContent().forEach(productDTO -> {
			System.out.println(productDTO);
		});
	}
	
	@Test
	public void testListWithReviewCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("pno"));
		
		Page<ProductListDTO> result = productRepository.listWithReviewCount(pageable);
		
		result.getContent().forEach(productListDTO -> {
			System.out.println(productListDTO);
		});
	}
	
	@Test
	public void testListQuery() {
		// 페이지 번호와 사이즈 설정 (1 페이지, 10개의 데이터)
		Pageable pageable = PageRequest.of(0, 10);
		
		// listQuery 메서드를 사용하여 페이징된 결과를 가져옴
		Page<ProductDTO> result = productRepository.listQuery(pageable);
		
		// 첫 번째 상품의 정보 출력
		result.getContent().forEach(productDTO -> {
			System.out.println(productDTO);
		});
	}
	
	@Test
	public void testListFetchAllImages() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
		Page<ProductDTO> result = productRepository.listFetchAllImages(pageable);
		
		for(ProductDTO productDTO : result.getContent()) {
			System.out.println(productDTO);
		}
		
	}
	
	@Transactional
	@Test
	public void testListWithAllImages() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
		Page<ProductDTO> result = productRepository.listWithAllImages(pageable);
		result.getContent().forEach(productDTO -> {
			System.out.println(productDTO);
		});
	}
	
	@Test
	public void testList() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
		Page<ProductListDTO> result = productRepository.list(pageable);
		result.getContent().forEach(productListDTO -> {
			System.out.println(productListDTO);
		});
	}
	
	@Test
	public void testReadDTO() {
		Long pno = 10L;
		Optional<ProductDTO> result = productRepository.getProductDTO(pno);
		ProductDTO productDTO = result.get();
		System.out.println(productDTO);
	}
	
	@Test
	@Transactional
	@Commit
	public void testDelete() {
		productRepository.deleteById(1L);
	}
	
	@Test
	@Transactional
	@Commit
	public void testUpdate() {
		Optional<ProductEntity> result = productRepository.getProduct(1L);
		ProductEntity productEntity = result.get();
		productEntity.changeTitle("변경된 상품");
		productEntity.changePrice(100000);
		productEntity.addImage("new1.jpg");
		productEntity.addImage("new2.jpg");
	}
	
	@Test
	public void testReadQuery(){
		//@EntityGraph 사용
		Long pno = 1L;
		Optional<ProductEntity> result = productRepository.getProduct(pno);
		ProductEntity productEntity = result.get();
		System.out.println(productEntity);
		System.out.println("=================");
		System.out.println(productEntity.getImages());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testRead() {
		//지연 로딩
		Long pno = 1L;
		
		Optional<ProductEntity> result = productRepository.findById(pno);
		ProductEntity productEntity = result.get();
		System.out.println(productEntity);
		System.out.println("======================");
		System.out.println(productEntity.getImages());
	}
	
	@Test
	@Transactional
	@Commit
	public void testInsert() {
		for(int i = 1; i <= 50; i++) {
			ProductEntity productEntity = ProductEntity.builder()
					.pname(i + "_새로운 상품")
					.price(5000)
					.content(i + "_상품 설명")
					.writer("user00")
					.build();
			
			productEntity.addImage(i + "_test1.jpg");
			productEntity.addImage(i + "_test2.jpg");
			
			productRepository.save(productEntity);
			System.out.println("New Product no: " + productEntity.getPno());
		}
	}
}
