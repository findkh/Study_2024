package com.kh.real_world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RealWorldSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealWorldSpringbootApplication.class, args);
	}

}
