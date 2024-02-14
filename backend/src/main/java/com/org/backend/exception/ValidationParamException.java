package com.org.backend.exception;

public class ValidationParamException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidationParamException() {
		super();
	}

	public ValidationParamException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationParamException(String message) {
		super(message);
	}

	public ValidationParamException(Throwable cause) {
		super(cause);
	}
}
