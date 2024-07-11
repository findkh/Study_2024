package com.kh.springboot_02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import com.kh.springboot_02.configurationProperties.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class) //@ConfigurationProperties 애너테이션이 붙어 있는 클래스를 스프링 컨테이너에 등록한다., 자동 탐색이 아니라 직접 명시해줘야함
public class Springboot02Application {
	
	private static final Logger log = LoggerFactory.getLogger(Springboot02Application.class);
		
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Springboot02Application.class, args);
		AppService appService = applicationContext.getBean(AppService.class);
		log.info(appService.getAppProperties().toString());
	}

}
