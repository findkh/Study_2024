package com.kh.ex3.member.exception;

import lombok.Getter;

@Getter
public class MemberTaskException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String msg;
	private int code;
	
	public MemberTaskException(String msg, int code) {
		this.msg = msg;
		this.code = code;
	}
}
