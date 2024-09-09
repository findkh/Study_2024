package com.kh.ex3.upload.controller.advice;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.kh.ex3.upload.exception.UploadNotSupportedException;

@RestControllerAdvice
public class FileControllerAdvice {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exception) {
		return ResponseEntity.badRequest().body(Map.of("error", "File too Large"));
	}
	
	@ExceptionHandler(UploadNotSupportedException.class)
	public ResponseEntity<Map<String, Object>> handleUploadNotSupportedException(UploadNotSupportedException exception){
		return ResponseEntity.badRequest().body(Map.of("error", exception.getMessage()));
	}
}
