package com.kh.springboot_02_4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot024Application {
	private static Logger logger = LoggerFactory.getLogger(Springboot024Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Springboot024Application.class, args);
		logger.info("애플리케이션이 시작됩니다. Log4j2와 함께...");
	}

}
