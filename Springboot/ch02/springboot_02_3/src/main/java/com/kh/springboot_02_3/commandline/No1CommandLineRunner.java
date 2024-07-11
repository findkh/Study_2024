package com.kh.springboot_02_3.commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class No1CommandLineRunner implements CommandLineRunner {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String... args) throws Exception {
		logger.info("첫번째 실행");
	}
}
