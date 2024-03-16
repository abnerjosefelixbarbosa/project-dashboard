package com.org.backend.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EmailRequest(
		@NotNull(message = "email invalid")
		@NotEmpty(message = "email invalid")
		@Email(message = "email invalid")
		String email
) {}