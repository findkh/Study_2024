package com.kh.ExceptionTest.exceptions;

public enum TestExceptions {
	NOT_FOUND("fail", "NOT_FOUND", 404), 
	DUPLICATE("fail", "DUPLICATE", 409),
	INVALID("fail", "INVALID", 400),
	BAD_CREDENTIALS("fail", "BAD_CREDENTIALS", 401);
	
	private TestTaskException testTaskException;
	
	TestExceptions(String result, String msg, int code) {
		testTaskException = new TestTaskException(result, msg, code);
	}
	
	public TestTaskException get() {
		return testTaskException;
	}
}
