package com.org.backend.dtos.requests;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectRequest(
		@NotNull(message = "name invalid") @Length(min = 1, max = 100, message = "name invalid") String name,
		@NotNull(message = "description invalid") @Length(min = 1, max = 200, message = "description invalid") String description,
		@NotNull(message = "date start invalid") @Future(message = "date start invalid") Date dateStart,
		@NotNull(message = "date end invalid") Date dateEnd, @NotNull(message = "budget invalid") BigDecimal budget) {
}