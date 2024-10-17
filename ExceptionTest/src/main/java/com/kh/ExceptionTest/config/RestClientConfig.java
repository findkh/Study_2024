package com.kh.ExceptionTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	@Bean
	public RestClient restClient() {
		System.out.println("!!!!!!!!!!!!!!");
		return RestClient.builder()
			.defaultHeader("Content-Type", "application/json")
			.defaultHeader("Accept", "application/json")
			.requestInterceptor(new RestLogFilter())
			.build();
	}
}
