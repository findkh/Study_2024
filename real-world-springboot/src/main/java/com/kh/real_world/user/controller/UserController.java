package com.kh.real_world.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.real_world.user.dto.UserDTO;
import com.kh.real_world.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody UserDTO userDTO) {
		System.out.println("/register 호출");
		userService.registerUser(userDTO);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> updatePwd(@RequestBody UserDTO userDTO) {
		log.info("/update 호출");
		userService.updatePwd(userDTO);
		return ResponseEntity.ok().build();
	}
	
}
