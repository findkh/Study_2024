package com.kh.demoapi.controller;

import java.net.SocketException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SocketController {

    @MessageMapping("/session/{callId}")
    @SendToUser("/status/init/{callId}")
    public String subscribeToInit(@DestinationVariable("callId") String callId, Principal principal)
            throws SocketException {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("callId", callId);
            data.put("msgKey", "home");

            return data.toString();
        } catch (Exception e) {
            throw new SocketException();
        }
    }

    @MessageMapping("/session/{callId}/unregister")
    public void subscribeExpire(@DestinationVariable("callId") String callId, Principal principal) {
        String sessionId = principal.getName();
        try {
//            subscriptionService.unregisterSession(callId, principal.getName());
//            logger.info("[@Stomp Close by client]: {}", sessionId).toJson();
        } catch (Exception e) {
//            throw new SocketException("[@Failed] to unsubscribe session for Call ID: " + callId, callId);
        }
    }
}