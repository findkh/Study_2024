package com.kh.ex3.review.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex3.review.dto.ReviewDTO;
import com.kh.ex3.review.dto.ReviewPageRequestDTO;
import com.kh.ex3.review.exception.ReviewExceptions;
import com.kh.ex3.review.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@PostMapping("")
	public ResponseEntity<ReviewDTO> register(@RequestBody @Validated ReviewDTO reviewDTO, Principal principal){
		System.out.println("호출됨!");
		log.info("register: " + reviewDTO);
		
		if(!principal.getName().equals(reviewDTO.getReviewer())) {
			throw ReviewExceptions.REVIEWER_MISMATCH.get();
		}
		
		return ResponseEntity.ok(reviewService.register(reviewDTO));
	}
	
	@GetMapping("/{rno}")
	public ResponseEntity<ReviewDTO> read(@PathVariable("rno") Long rno){
		log.info("read: " + rno);
		return ResponseEntity.ok(reviewService.read(rno));
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<Map<String, String>> remove(@PathVariable("rno") Long rno, Authentication authentication){
		log.info("remove: " + rno);
		String currentUser = authentication.getName();
		
		log.info("current User: " + currentUser);
		
		ReviewDTO reviewDTO = reviewService.read(rno);
		
		if(!currentUser.equals(reviewDTO.getReviewer())) {
			throw ReviewExceptions.REVIEWER_MISMATCH.get();
		}
		
		reviewService.remove(rno);
		
		return ResponseEntity.ok().body(Map.of("result", "success"));
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<ReviewDTO> modify(@PathVariable("rno") Long rno, @RequestBody ReviewDTO reviewDTO, Authentication authentication) {
		log.info("modify: " + rno);
		
		if(!rno.equals(reviewDTO.getRno())) {
			throw ReviewExceptions.REVIEW_NOT_MATCHED.get();
		}
		
		String currentUser = authentication.getName();
		log.info("currentUser: " + currentUser);
		
		if(!currentUser.equals(reviewDTO.getReviewer())) {
			throw ReviewExceptions.REVIEWER_MISMATCH.get();
		}
		
		return ResponseEntity.ok().body(reviewService.modify(reviewDTO));
	}
	
	@GetMapping("/{pno}/list")
	public ResponseEntity<?> list(@PathVariable("pno") Long pno, @Validated ReviewPageRequestDTO pageRequestDTO) {
		pageRequestDTO.setPno(pno);
		return ResponseEntity.ok(reviewService.getList(pageRequestDTO));
	}
}