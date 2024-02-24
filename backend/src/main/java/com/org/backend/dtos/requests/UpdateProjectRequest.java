package com.org.backend.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectRequest(
		@NotNull(message = "Name null") @Length(min = 1, max = 100, message = "Name at min 1 and max 100") String name,
		@NotNull(message = "Description null") @Length(min = 1, max = 200, message = "Description at min 1 and max 200") String description,
		@NotNull(message = "Date start null") @Future(message = "Date start future") LocalDate dateStart,
		@NotNull(message = "Date end null") LocalDate dateEnd, @NotNull(message = "Budget null") BigDecimal budget) {
}