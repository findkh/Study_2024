package com.kh.ex1.sample;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTests {
	
	@Autowired(required = false)
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testhello() {
		String[] result = testRestTemplate.getForObject("/api/v1/sample/hello", String[].class);
		System.out.println(Arrays.toString(result));
	}
	
}
