package com.kh.mallapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.mallapi.controller.formatter.LocalDateFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer {

    // 날짜 포매터를 등록하는 메서드
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new LocalDateFormatter());
    }

    // Spring Security 설정 전 CORS 설정
    // CORS (Cross-Origin Resource Sharing) 설정을 추가하는 메서드
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // registry.addMapping("/**")
    // .allowedOrigins("*") // 모든 오리진 허용
    // .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용하는
    // HTTP 메서드 지정
    // .maxAge(300) // Preflight 요청 결과를 캐시하는 시간 (초 단위)
    // .allowedHeaders("Authorization", "Cache-Control", "Content-Type"); // 허용하는 헤더
    // 지정
    // }

}