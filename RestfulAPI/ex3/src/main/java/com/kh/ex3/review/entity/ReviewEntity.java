package com.kh.ex3.review.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kh.ex3.product.entity.ProductEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_reviews", indexes = @Index(columnList = "product_pno"))
@Getter
@ToString(exclude = "productEntity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = {AuditingEntityListener.class})
public class ReviewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	
	private String reviewText;
	
	private String reviewer;
	
	private int score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_pno")
	private ProductEntity productEntity;
	
	@CreatedDate
	private LocalDateTime reviewDate;
	
	@LastModifiedDate
	private LocalDateTime modifedDate;
	
	public void changeReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	public void changeScore(int score) {
		this.score = score;
	}
}
