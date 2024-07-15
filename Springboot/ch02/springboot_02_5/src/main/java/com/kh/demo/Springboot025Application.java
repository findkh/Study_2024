package com.kh.demo;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kh.demo.model.Course;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@SpringBootApplication
public class Springboot025Application implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(Springboot025Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Springboot025Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = new Course();
		course.setId(1);
		course.setRating(0);
		
		Validator validater = Validation.buildDefaultValidatorFactory().getValidator();
		
		Set<ConstraintViolation<Course>> violations = validater.validate(course);
		violations.forEach(courseConstraintViolation -> 
		logger.info("A consgtraint violation has occurred. Violation defails: [{}].", courseConstraintViolation));
		
	}

}
