package com.org.backend.exception;

import java.time.LocalDateTime;

public record ExceptionDetails(
		String title,
		LocalDateTime localDateTime,
		Integer status,
		String exception
) {

}
