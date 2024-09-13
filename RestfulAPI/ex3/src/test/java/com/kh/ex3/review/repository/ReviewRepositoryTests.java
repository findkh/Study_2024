package com.kh.ex3.review.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex3.product.entity.ProductEntity;
import com.kh.ex3.review.entity.ReviewEntity;
import com.kh.ex3.review.exception.ReviewExceptions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTests {
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	public void testList() {
		Long pno = 2L;
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
		reviewRepository.getListByPno(pno, pageable).getContent().forEach(reviewDTO -> {
			System.out.println(reviewDTO);
		});
	}
	
	@Transactional
	@Test
	@Commit
	public void testUpdate() {
		Long rno = 11L;
		ReviewEntity reviewEntity = reviewRepository.findById(rno).orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);
		reviewEntity.changeReviewText("변경된 리뷰 내용");
		reviewEntity.changeScore(3);
	}
	
	@Transactional
	@Test
	@Commit
	public void testRemove2() {
		try {
			ReviewEntity reviewEntity = reviewRepository.findById(1L).orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);
			reviewRepository.delete(reviewEntity);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@Test
	@Commit
	public void testRemove() {
		Long rno = 10L;
		reviewRepository.deleteById(rno);
	}
	
	@Test
	public void testGetWithProduct() {
		Long rno = 10L;
		reviewRepository.getWithProduct(rno).ifPresent(reviewEntity -> {
			System.out.println(reviewEntity);
			System.out.println(reviewEntity.getProductEntity());
			System.out.println(reviewEntity.getProductEntity().getImages());
		});
	}
	
	@Transactional
	@Test
	public void testRead() {
		Long rno = 10L;
		reviewRepository.findById(rno).ifPresent(reviewEntity -> {
			System.out.println(reviewEntity);
			System.out.println(reviewEntity.getProductEntity());
		});
	}
	
	@Test
	@Commit
	public void testInsert() {
		Long pno = 2L;
//		Long pno = 999L;
		
		ProductEntity productEntity = ProductEntity.builder().pno(pno).build();
		
		System.out.println("==========ProductEntity==========");
		System.out.println(productEntity);
		
		ReviewEntity reviewEntity = ReviewEntity.builder()
				.reviewText("리뷰 내용...")
				.score(5)
				.reviewer("reviewer1")
				.productEntity(productEntity)
				.build();
		
		reviewRepository.save(reviewEntity);
	}
}
