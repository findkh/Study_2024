package com.kh.real_world.follow.exception;

public enum FollowExceptions {
	NOT_FOUND("NOT_FOUND", 404),
	DUPLICATE("DUPLICATE", 409),
	INVALID("INVALID", 400),
	BAD_CREDENTIALS("BAD_CEDENTIALS", 401);
	
	private FollowTaskException followTaskException;
	
	FollowExceptions(String msg, int code) {
		followTaskException = new FollowTaskException(msg, code);
	}
	
	public FollowTaskException get() {
		return followTaskException;
	}
}
