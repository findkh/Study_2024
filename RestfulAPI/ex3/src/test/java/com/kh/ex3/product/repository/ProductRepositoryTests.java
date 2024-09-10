package com.kh.ex3.product.repository;



import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex3.product.entity.ProductEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository productRepository;
	
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
