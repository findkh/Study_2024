package com.kh.real_world.profile.exception;


public enum ProfileExceptions {
	NOT_FOUND("Profile Not Found", 404),
	INVALID("Profile Invalid", 400),
	BAD_CREDENTIALS("Bad credentials", 401);
	
	private ProfileTaskException profileTaskException; 
	
	ProfileExceptions(String msg, int code) {
		profileTaskException = new ProfileTaskException(msg, code);
	}
	
	public ProfileTaskException get() {
		return profileTaskException;
	}
}
