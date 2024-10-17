package com.kh.ExceptionTest.advice;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kh.ExceptionTest.exceptions.TestTaskException;

@ControllerAdvice
public class testControllerAdvice {
	
	@ExceptionHandler(TestTaskException.class)
	public ResponseEntity<Map<String, Object>> handleTestTaskException(TestTaskException e) {
		return handleException(e.getResult(), e.getMsg(), e.getCode());
	}
	
	
	private ResponseEntity<Map<String, Object>> handleException(String result, String msg, int code){
		Map<String, Object> response = Map.of("result", result, "msg", msg, "code", code);
		return ResponseEntity.status(code).body(response);
	}
}
