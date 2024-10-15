package com.kh.real_world.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.real_world.profile.dto.ProfileDTO;
import com.kh.real_world.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/profile")
@Log4j2
@RequiredArgsConstructor
public class ProfileController {
	
	private final ProfileService profileService;
	
	@GetMapping("/")
	public ResponseEntity<ProfileDTO> getProfile(@RequestParam(name = "email") String email) {
		log.info("Profile GET 호출됨");
		
		String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		
		ProfileDTO profileDTO = profileService.getProfile(loggedInUserEmail, email);
		
		return ResponseEntity.ok(profileDTO);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) {
		log.info("Profile UPDATE 호출");
		ProfileDTO result = profileService.updateProfile(profileDTO);
		return ResponseEntity.ok(result);
	}
}
