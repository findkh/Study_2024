package com.kh.springboot_02_3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/* 스프링 부트 애플리케이션 시작 시 CommandLineRunner로 코드 실행하는 방법
 * 1. 스프링 부트 메인 클래스가 CommandLineRunner 인터페이스를 구현하게 만든다.
 * */

//@SpringBootApplication
//public class Springboot023Application implements CommandLineRunner {
//	
//	protected final Log logger = LogFactory.getLog(getClass());
//
//	public static void main(String[] args) {
//		SpringApplication.run(Springboot023Application.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		logger.info("스프링 부트 애플리케이션 시작시 실행 코드!!");
//		
//	}
//}


/* 스프링 부트 애플리케이션 시작 시 CommandLineRunner로 코드 실행하는 방법
 * 2. CommandLineRunner 구현체에 @Bean을 붙여서 빈으로 정의한다.
 * 3. CommandLineRunner 구현체에 @Components를 붙여서 스프링 컴포넌트로 정의한다.
 * */
@SpringBootApplication
public class Springboot023Application implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());

	public static void main(String[] args) {
		SpringApplication.run(Springboot023Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("CourseTrackerApplication CommandLineRunner has executed");
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			logger.info("CommandLineRunner executed as a bean definition with "+args.length +" arguments");
			for(int i=0; i<args.length;i++){
				logger.info("Argument: "+args[i]);
			}
		};
	}
}
