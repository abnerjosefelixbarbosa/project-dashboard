package com.org.backend.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectRequest(
		@NotNull(message = "Name null")
		@Length(max = 100, message = "Name max 100")
		@NotEmpty(message = "Name empty")
		String name,
		@NotNull(message = "Description null")
		@Length(max = 200, message = "Description max 200")
		@NotEmpty(message = "Description empty")
		String description,
		@NotNull(message = "Date start null")
		@Future(message = "Date start future")
		LocalDate dateStart,
		@NotNull(message = "Date end null")
		LocalDate dateEnd,
		@NotNull(message = "Budget null")
		BigDecimal budget
) {}