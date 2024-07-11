package com.kh.springboot_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication
 * ->@SpringBootApplication 애너테이션은 @EnableAutoConfiguration, @ComponentScan, @SpringBootConfiguration 3개의 애너테이션을 포함한다.
 * @EnableAutoConfiguration
 * 		-> 애플리케이션 클래스 패스에 있는 JAR 파일을 바탕으로 애플리케이션을 자동으로 구성해주는 스프링 부트 자동 구성 기능을 활성화한다.
 * @ComponentScan
 * 		-> 애플리케이션에 있는 스프링 컴포넌트를 탐색해서 찾아낸다.
 * 		   루트 패키지와 그 하위 패키지에 존재하는 컴포넌트를 탐색하기 때문에 이 위치를 벗어난 컴포넌트는 탐색 대상에 포함되지 않는다.(스프링부트가관리하지 않는다.)
 * @SpringBootConfiguration
 * 		-> 스프링 부트 애플리케이션 설정을 담당하는 클래스에 붙이는 애너테이션.
*/
//@SpringBootApplication
//public class Springboot01Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Springboot01Application.class, args);
//		/*	run() 실행과정
//		 * 1. 클래스패스에 있는 라이브러리를 기준으로 ApplicationContext 클래스 인스턴스를 생성한다.
//		 * 2. CommandLinePropertySource를 등록해서 명령행 인자를 스프링 프로퍼티로 읽는다.
//		 * 3. 1단계에서 생성한 ApplicationContext를 통해 모든 싱글턴 빈을 로딩한다.
//		 * 4. 애플리케이션에 설정된 ApplicationRunners와 CommandRunners를 실행한다.
//		 * */
//	}
//
//}

//SpringApplication 인스턴스를 직접 생성해서 웹 애플리케이션 타입을 리액티브로 지정
@SpringBootApplication
public class Springboot01Application{
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Springboot01Application.class); //springApplication 인스턴스 생성
		springApplication.setWebApplicationType(WebApplicationType.REACTIVE); //springApplication의 웹 애플리케이션 타입을 리액티브로 지정
		springApplication.run(args);
	}
}
