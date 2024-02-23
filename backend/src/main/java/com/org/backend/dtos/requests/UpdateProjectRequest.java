package com.org.backend.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectRequest(
		@NotNull(message = "name null") @Length(min = 1, max = 100, message = "name at min 1 and max 100") String name,
		@NotNull(message = "description null") @Length(min = 1, max = 200, message = "description at min 1 and max 200") String description,
		@NotNull(message = "date start null") @Future(message = "date start invalid") LocalDate dateStart,
		@NotNull(message = "date end null") LocalDate dateEnd, @NotNull(message = "budget null") BigDecimal budget) {
}