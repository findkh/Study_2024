package com.kh.ExceptionTest.exceptions;

import lombok.Getter;

@Getter
public class TestTaskException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String result;
	private String msg;
	private int code;
	
	public TestTaskException(String result, String msg, Integer code) {
		this.result = result;
		this.msg = msg;
		this.code = code;
	}
}
