package com.kh.ex3.upload.exception;

public class UploadNotSupportedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UploadNotSupportedException(String message) {
		super(message);
	}
}
