package com.kh.ex1.sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex1.sample.service.SampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/sample")
@Log4j2
@RequiredArgsConstructor
public class SampleController {
	
	private final SampleService sampleService = new SampleService();
	
	@RequestMapping("/hello")
	public String[] hello() {
		return new String[] {"Hello", "World!"};
	}
}
