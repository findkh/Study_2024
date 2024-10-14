package com.kh.real_world.user.exception;

public enum UserExceptions {
	NOT_FOUND("NOT_FOUND", 404),
	DUPLICATE("DUPLICATE", 409),
	INVALID("INVALID", 400),
	BAD_CREDENTIALS("BAD_CEDENTIALS", 401);
	
	private UserTaskException userTaskException; 
	
	// 예외 메시지와 상태 코드를 사용하여 UserTaskException을 초기화
	UserExceptions(String msg, int code) {
		userTaskException = new UserTaskException(msg, code);
	}
	
	// UserTaskException 인스턴스를 반환하는 메소드
	public UserTaskException get() {
		return userTaskException;
	}
}
