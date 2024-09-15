package com.kh.ex3.review.dto;

import java.time.LocalDateTime;

import com.kh.ex3.product.entity.ProductEntity;
import com.kh.ex3.review.entity.ReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReviewDTO {
	private Long rno;
	
	private String reviewText;
	
	private String reviewer;
	
	private int score;
	
	private Long pno;
	
	private LocalDateTime reviewDate;
	
	private LocalDateTime modifiedDate;
	
	public ReviewDTO(ReviewEntity reviewEntity) {
		this.rno = reviewEntity.getRno();
		this.reviewText = reviewEntity.getReviewText();
		this.reviewer = reviewEntity.getReviewer();
		this.score = reviewEntity.getScore();
		this.pno = reviewEntity.getProductEntity().getPno();
		this.reviewDate = reviewEntity.getReviewDate();
		this.modifiedDate = reviewEntity.getModifedDate();
	}
	
	public ReviewEntity toEntity() {
		ProductEntity productEntity = ProductEntity.builder().pno(pno).build();
		return ReviewEntity.builder()
				.rno(rno)
				.reviewText(reviewText)
				.reviewer(reviewer)
				.score(score)
				.productEntity(productEntity)
				.build();
	}
}