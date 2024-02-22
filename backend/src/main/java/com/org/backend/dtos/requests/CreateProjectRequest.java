package com.org.backend.dtos.requests;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record CreateProjectRequest(
		@NotNull(message = "name null") @Length(min = 1, max = 100, message = "name at min 1 and max 100") String name,
		@NotNull(message = "description null") @Length(min = 1, max = 200, message = "description at min 1 and max 100") String description,
		@NotNull(message = "date start null") @Future(message = "date start invalid") Date dateStart,
		@NotNull(message = "date end null") Date dateEnd, @NotNull(message = "budget null") BigDecimal budget,
		@NotNull(message = "account id null") String accountId
) {}