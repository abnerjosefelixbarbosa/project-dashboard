package com.org.backend.exception;

public class ValidationParamException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidationParamException(String message) {
		super(message);
	}
}
