package com.kh.ExceptionTest.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ExceptionTest.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class testController {
	
	private final TestService testService;
	
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> test() {
		return testService.getData();
	}
}
