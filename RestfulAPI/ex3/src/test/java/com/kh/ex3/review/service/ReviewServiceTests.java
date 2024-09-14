package com.kh.ex3.review.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.ex3.review.dto.ReviewDTO;

@SpringBootTest
public class ReviewServiceTests {
	
	@Autowired
	private ReviewService reviewService;
	
	
	@Test
	public void testRead() {
		try {
			Long rno = 1L;
			ReviewDTO reviewDTO = reviewService.read(rno);
			System.out.println(reviewDTO);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//	
//	@Test
//	public void testRegister() {
//		try {
//			Long pno = 1000L;
//			ReviewDTO reviewDTO = ReviewDTO.builder()
//					.reviewText("리뷰 내용")
//					.score(5)
//					.reviewer("reviewer1")
//					.pno(pno)
//					.build();
//			
//			reviewService.register(reviewDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
