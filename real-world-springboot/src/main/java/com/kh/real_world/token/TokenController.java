package com.kh.real_world.token;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.real_world.user.dto.UserDTO;
import com.kh.real_world.user.security.util.JWTUtil;
import com.kh.real_world.user.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/token")
@Log4j2
@RequiredArgsConstructor
public class TokenController {
	
	private final UserService userService;
	
	private final JWTUtil jwtUtil;
	
	@PostMapping("/make")
	public ResponseEntity<Map<String, String>> makeToken(@RequestBody UserDTO userDTO){
		log.info("토큰 생성...");
		UserDTO userDTOResult = userService.login(userDTO.getEmail(), userDTO.getPassword());
		log.info(userDTOResult);
			
		String email = userDTOResult.getEmail();
		
		Map<String, Object> dataMap = userDTOResult.getDataMap();
		
		String accessToken = jwtUtil.createToken(dataMap, 10);
		String refreshToken = jwtUtil.createToken(Map.of("email", email), 60 * 24 * 7);
		
		log.info("accessToken: " + accessToken);
		log.info("refreshToken: " + refreshToken);
		
		return ResponseEntity.ok(Map.of("accessToken", accessToken, "refershToken", refreshToken));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refreshToken(
			@RequestHeader("Authorization") String accessTokenStr,
			@RequestParam("refreshToken") String refreshToken,
			@RequestParam("email") String email
			){
		
		// 토큰 존재 여부 확인
		log.info("access token with Bearer..." + accessTokenStr);
		
		if(accessTokenStr == null || !accessTokenStr.startsWith("Bearer")) {
			return handleException("No Access Token", 400);
		}
		
		if(refreshToken == null) {
			return handleException("No Refresh Token", 400);
		}
		
		log.info("refresh token: " + refreshToken);
		
		if(email == null) {
			return handleException("No Email", 400);
		}
		
		// access 토큰 만료 여부 확인
		String accessToken = accessTokenStr.substring(7);
		try {
			jwtUtil.validateToken(accessToken);
			
			// 기간이 남아 있는 경우
			Map<String, String> data = makeData(email, accessToken, refreshToken);
			
			log.info("Access Token is not expired.");
			
			return ResponseEntity.ok(data);
			
		} catch(ExpiredJwtException expiredJwtException) {
			// 리프레시 필요
			try {
				Map<String, String> newTokenMap = makeNewToken(email, refreshToken);
				return ResponseEntity.ok(newTokenMap);
			} catch(Exception e) {
				return handleException("REFRESH: " + e.getMessage(), 400);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return handleException(e.getMessage(), 400);
		}
	}
	
	private Map<String, String> makeNewToken(String email, String refreshToken) {
		Map<String, Object> claims = jwtUtil.validateToken(refreshToken);
		
		if(!email.equals(claims.get("email").toString())) {
			throw new RuntimeException("Invalid Refresh Token Host.");
		}
		
		// email로 사용자 정보 확인 후 새로운 토큰 발행
		UserDTO memberDTO = userService.getByEmail(email);
		Map<String, Object> newClaims = memberDTO.getDataMap();
		String newAccessToken = jwtUtil.createToken(newClaims, 10);
		String newRefreshToken = jwtUtil.createToken(Map.of("email", email), 60 * 24 * 7);
		
		return makeData(email, newAccessToken, newRefreshToken);
	}
	
	private Map<String, String> makeData(String email, String accessToken, String refreshToken){
		return Map.of("email", email, "accessToken", accessToken, "refreshToken", refreshToken);
	}
	
	private ResponseEntity<Map<String, String>> handleException(String msg, int status){
		return ResponseEntity.status(status).body(Map.of("error", msg));
	}
}