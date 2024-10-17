package com.kh.ExceptionTest.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kh.ExceptionTest.exceptions.TestExceptions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	private final RestClient restClient;

	public ResponseEntity<Map<String, Object>> getData() {
		ResponseEntity<Map<String, Object>> response = restClient
				.method(HttpMethod.GET)
				.uri("http://localhost:8081/duplicate")
				.retrieve()
				.toEntity(new ParameterizedTypeReference<Map<String, Object>>() {});

		// 상태 코드 확인 및 예외 처리
		checkResponseStatus(response);

		return response;
	}

	private void checkResponseStatus(ResponseEntity<Map<String, Object>> response) {
		int code = response.getStatusCode().value();

		// 응답 코드에 따라 적절한 예외를 발생시킴
		if (code != 200) {
			Map<String, Object> responseMap = response.getBody();
			if (responseMap != null) {
				switch (code) {
					case 404:
						throw TestExceptions.NOT_FOUND.get();
					case 409:
						throw TestExceptions.DUPLICATE.get();
					case 400:
						throw TestExceptions.INVALID.get();
					case 401:
						throw TestExceptions.BAD_CREDENTIALS.get();
					default:
						throw new RuntimeException("Unexpected error code: " + code);
				}
			} else {
				throw new RuntimeException("Response body is null");
			}
		}
	}
}