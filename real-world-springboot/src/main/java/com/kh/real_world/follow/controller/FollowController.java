package com.kh.real_world.follow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.real_world.follow.service.FollowService;
import com.kh.real_world.profile.dto.ProfileDTO;
import com.kh.real_world.profile.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/follow")
@Log4j2
@RequiredArgsConstructor
public class FollowController {
	
	private final FollowService followService;
	private final ProfileService profileService;
	
	@PostMapping("/update")
	public ResponseEntity<ProfileDTO> updateFollow(@RequestParam(name = "email") String email) {
		log.info("Follow UPDATE 호출");
		String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		followService.updateFollow(loggedInUserEmail, email);
		ProfileDTO profileDTO = profileService.getProfile(loggedInUserEmail, email);
		return ResponseEntity.ok(profileDTO);
	}
}
