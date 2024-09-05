package com.kh.ex2.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private int code;
	
	public EntityNotFoundException(String message) {
		super(message);
		this.message = message;
		this.code = 404;
	}
}
