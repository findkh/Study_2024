package com.kh.ex3.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.ex3.member.dto.MemberDTO;
import com.kh.ex3.member.security.util.JWTUtil;
import com.kh.ex3.member.service.MemberService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/token")
@Log4j2
@RequiredArgsConstructor
public class TokenController {
	private final MemberService memberService;
	
	private final JWTUtil jwtUtil;
	
	@PostMapping("/make")
	public ResponseEntity<Map<String, Object>> makeToken(@RequestBody MemberDTO memberDTO){
		log.info("make token..");
		
		MemberDTO memberDTOResult = memberService.read(memberDTO.getMid(), memberDTO.getMpw());
		
		String mid = memberDTOResult.getMid();
		Map<String, Object> dataMap = memberDTOResult.getDataMap();
		String accessToken = jwtUtil.createToken(dataMap, 10);
		String refreshToken = jwtUtil.createToken(Map.of("mid", mid), 60 * 24 * 7);
		
		log.info("accessToken: " + accessToken);
		log.info("refreshToken: " + refreshToken);
		
		return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refreshToken(
			@RequestHeader("Authorization") String accessTokenStr,
			@RequestParam("refreshToken") String refreshToken,
			@RequestParam("mid") String mid
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
		
		if(mid == null) {
			return handleException("No Mid", 400);
		}
		
		// access 토큰 만료 여부 확인
		String accessToken = accessTokenStr.substring(7);
		try {
			jwtUtil.validateToken(accessToken);
			
			// 기간이 남아 있는 경우
			Map<String, String> data = makeData(mid, accessToken, refreshToken);
			
			log.info("Access Token is not expired.");
			
			return ResponseEntity.ok(data);
			
		} catch(ExpiredJwtException expiredJwtException) {
			// 리프레시 필요
			try {
				Map<String, String> newTokenMap = makeNewToken(mid, refreshToken);
				return ResponseEntity.ok(newTokenMap);
			} catch(Exception e) {
				return handleException("REFRESH: " + e.getMessage(), 400);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return handleException(e.getMessage(), 400);
		}
	}
	
	private Map<String, String> makeNewToken(String mid, String refreshToken) {
		Map<String, Object> claims = jwtUtil.validateToken(refreshToken);
		
		if(!mid.equals(claims.get("mid").toString())) {
			throw new RuntimeException("Invalid Refresh Token Host.");
		}
		
		// mid로 사용자 정보 확인 후 새로운 토큰 발행
		MemberDTO memberDTO = memberService.getByMid(mid);
		Map<String, Object> newClaims = memberDTO.getDataMap();
		String newAccessToken = jwtUtil.createToken(newClaims, 10);
		String newRefreshToken = jwtUtil.createToken(Map.of("mid", mid), 60 * 24 * 7);
		
		return makeData(mid, newAccessToken, newRefreshToken);
	}
	
	private Map<String, String> makeData(String mid, String accessToken, String refreshToken){
		return Map.of("mid", mid, "accessToken", accessToken, "refreshToken", refreshToken);
	}
	
	private ResponseEntity<Map<String, String>> handleException(String msg, int status){
		return ResponseEntity.status(status).body(Map.of("error", msg));
	}
}
