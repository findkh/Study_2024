package com.kh.real_world.profile.exception;


public enum ProfileExceptions {
	NOT_FOUND("NOT_FOUND", 404),
	DUPLICATE("DUPLICATE", 409),
	INVALID("INVALID", 400),
	BAD_CREDENTIALS("BAD_CEDENTIALS", 401);
	
	private ProfileTaskException profileTaskException; 
	
	ProfileExceptions(String msg, int code) {
		profileTaskException = new ProfileTaskException(msg, code);
	}
	
	public ProfileTaskException get() {
		return profileTaskException;
	}
}
