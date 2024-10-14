package com.kh.real_world.token.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.real_world.user.exception.UserTaskException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class TokenControllerAdvice {
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e){
		log.info("handle Access Denied Exception...");
		Map<String, Object> errors = new HashMap<>();
		errors.put("message", e.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UserTaskException.class)
	public ResponseEntity<Map<String, String>> handleTaskException(UserTaskException e){
		log.error(e.getMessage());
		
		String msg = e.getMsg();
		int status = e.getCode();
		
		Map<String, String> map = Map.of("error", msg);
		
		return ResponseEntity.status(status).body(map);
	}
}
