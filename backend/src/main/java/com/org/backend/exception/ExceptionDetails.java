package com.org.backend.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ExceptionDetails(@JsonFormat(pattern = "yyyy-MM-dd HH:ss") LocalDateTime localDateTime, Integer status, String exception, String path) {
}