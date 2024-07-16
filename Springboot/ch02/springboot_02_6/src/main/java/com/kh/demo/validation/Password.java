package com.kh.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD}) //@Password 애너테이션을 적용할 타입을 지
@Retention(RetentionPolicy.RUNTIME) //@Password 애너테이션이 언제까지 효력을 유지하고 살아남는지 지
@Constraint(validatedBy = PasswordRuleValidator.class) //@Password 애너테이션이 빈 벨리데이션 제약 사항을 포함하는 애너테이션임을 의미하며, validateBy 속성을 사용해서 제약 사항이 구현된 클래스를 지정할 수 있다.
public @interface Password {
	String message() default "Password do not adhere to the speciied rule";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
