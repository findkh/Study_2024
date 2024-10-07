package com.kh.stomp_websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.kh.stomp_websocket.DTO.InputMessage;
import com.kh.stomp_websocket.DTO.OutputMessage;

@Controller
public class MessageController {
	// 클라이언트가 "/app/message"로 메시지를 전송하면
	@MessageMapping("/message")
	// "/topic/messages"를 구독하는 클라이언트들에게 메시지를 브로드캐스트
	@SendTo("/topic/messages")
	public OutputMessage sendMessage(InputMessage message) throws Exception {
		// 클라이언트에게 전송할 메시지 생성
		return new OutputMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
}
