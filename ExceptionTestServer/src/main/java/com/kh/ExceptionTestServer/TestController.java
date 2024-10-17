package com.kh.ExceptionTestServer;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TestController {

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getData() {
		Map<String, Object> response = Map.of(
			"result", "success",
			"msg", "data",
			"code", 200
		);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/not-found")
	public ResponseEntity<Map<String, Object>> getNotFound() {
		Map<String, Object> response = Map.of(
			"result", "fail",
			"msg", "NOT_FOUND",
			"code", 404
		);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/duplicate")
	public ResponseEntity<Map<String, Object>> getDuplicate() {
		Map<String, Object> response = Map.of(
			"result", "fail",
			"msg", "DUPLICATE",
			"code", 409
		);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/invalid")
	public ResponseEntity<Map<String, Object>> getInvalid() {
		Map<String, Object> response = Map.of(
			"result", "fail",
			"msg", "INVALID",
			"code", 400
		);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/bad-credentials")
	public ResponseEntity<Map<String, Object>> getBadCredentials() {
		Map<String, Object> response = Map.of(
			"result", "fail",
			"msg", "BAD_CREDENTIALS",
			"code", 401
		);
		return ResponseEntity.ok(response);
	}
}
