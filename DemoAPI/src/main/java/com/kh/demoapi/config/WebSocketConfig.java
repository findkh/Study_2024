package com.kh.demoapi.config;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSocketMessageBroker // WebSocket 메시지 브로커를 활성화
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    private final ApplicationContext context;

    static class CustomHandshakeHandler extends DefaultHandshakeHandler {

        @Override
        protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
            // 사용자 고유 식별자 생성
            String userId = UUID.randomUUID().toString();
            return new Principal() {
                @Override
                public String getName() {
                    return userId;
                }
            };
        }
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connectUrlPush")
                .setAllowedOriginPatterns("*")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/push");        // 클라이언트가 보내는 주소 prefix
        registry.enableSimpleBroker("/status");  // 클라이언트가 구독을 하는 prefix
    }



}
