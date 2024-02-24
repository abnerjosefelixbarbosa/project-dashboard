package com.org.backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExcepitionController {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(ValidationParamException.class)
	public ResponseEntity<ExceptionDetails> handleValidationParamException(Exception e, HttpServletRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), 400, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(400).body(exceptionDetails);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionDetails> handleBusinessException(BusinessException e, HttpServletRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), 400, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(400).body(exceptionDetails);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), 404, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(404).body(exceptionDetails);
	}
}
