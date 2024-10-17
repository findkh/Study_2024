package com.kh.ExceptionTest.config;


import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestLogFilter implements ClientHttpRequestInterceptor {

	/**
	 * 클라이언트 요청을 가로채고, 요청 및 응답에 대한 정보를 로그로 기록.
	 * 
	 * @param request - HTTP 요청 객체
	 * @param body - 요청 본문 데이터
	 * @param execution - 요청 실행 객체
	 * @return ClientHttpResponse - 요청에 대한 응답 객체
	 * @throws IOException - 입출력 예외 처리
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		long start = System.currentTimeMillis();
		ClientHttpResponse response = execution.execute(request, body);
		long duration = System.currentTimeMillis() - start;
		logRequestAndResponse(request, response, duration);
		return response;
	}
	
	/**
	 * 요청 및 응답 정보를 로그로 기록.
	 * 
	 * @param request - HTTP 요청 객체
	 * @param response - HTTP 응답 객체
	 * @param duration - 요청 처리 시간 (ms)
	 */
	private void logRequestAndResponse(HttpRequest request, ClientHttpResponse response, long duration) {
		try {
			log.info("Request URI: {}, Duration: {}ms", request.getURI(), duration);
			log.info("Response Status Code: {}", response.getStatusCode());
		} catch (IOException e) {
			throw new RestClientException("Rest Client 통신 중 에러", e);
		}
	}
}
