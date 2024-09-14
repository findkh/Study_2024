package com.kh.ex3.review.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ex3.review.dto.ReviewDTO;
import com.kh.ex3.review.dto.ReviewPageRequestDTO;
import com.kh.ex3.review.entity.ReviewEntity;
import com.kh.ex3.review.exception.ReviewExceptions;
import com.kh.ex3.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	public ReviewDTO register(ReviewDTO reviewDTO) {
		log.info("review register...");
		
		try {
			ReviewEntity reviewEntity = reviewDTO.toEntity();
			
			reviewRepository.save(reviewEntity);
			
			return new ReviewDTO(reviewEntity);
		} catch(DataIntegrityViolationException e) {
			throw ReviewExceptions.REVIEW_PRODUCT_NOT_FOUND.get();
		} catch(Exception e) {
			log.error(e.getMessage());
			throw ReviewExceptions.REVIEW_NOT_REGISTERED.get();
		}
	}
	
	public ReviewDTO read(Long rno) {
		ReviewEntity reviewEntity = reviewRepository.findById(rno).orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);
		return new ReviewDTO(reviewEntity);
	}
	
	public void remove(Long rno) {
		ReviewEntity reviewEntity = reviewRepository.findById(rno).orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);
		try {
			reviewRepository.delete(reviewEntity);
		} catch(Exception e) {
			log.error(e.getMessage());
			throw ReviewExceptions.REVIEW_NOT_REMOVE.get();
		}
	}
	
	public Page<ReviewDTO> getList(ReviewPageRequestDTO reviewPageRequestDTO){
		Long pno = reviewPageRequestDTO.getPno();
		Pageable pageable = reviewPageRequestDTO.getPageable(Sort.by("rno").descending());
		return reviewRepository.getListByPno(pno, pageable);
	}
}
