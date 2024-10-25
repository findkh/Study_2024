package com.kh.server.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {

private final ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper 인스턴스 생성

	@MessageMapping("/send") // "/app/send"에 도달하는 메시지를 처리합니다.
	@SendTo("/topic/messages") // "/topic/messages"로 메시지를 전송합니다.
	public String sendMessage(String message) {
		System.out.println("연결되었습니다."); // 연결 되었음을 콘솔에 출력
		try {
			// JSON 문자열을 Map으로 변환
			@SuppressWarnings("unchecked")
			Map<String, String> messageMap = objectMapper.readValue(message, Map.class);
			String callId = messageMap.get("callId");
			System.out.println("Received callId: " + callId); // callId 콘솔에 출력
			
			// 응답 메시지 형성 (성공 또는 실패)
			if (callId != null && callId != "") {
				return "{\"status\": \"success\"}"; // 성공 메시지
			} else {
				return "{\"status\": \"fail\"}"; // 실패 메시지
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"fail\"}"; // 실패 메시지
		}
	}
}
