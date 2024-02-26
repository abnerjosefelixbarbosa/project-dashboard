package com.org.backend.dtos.requests;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginAccountRequest(
		@NotNull(message = "Email user null")
		@Email(message = "Email user invalid")
		@NotEmpty(message = "Email user empty")
		String emailUser,
		@NotNull(message = "Password user null")
		@NotEmpty(message = "Password user empty")
		@Length(max = 20, message = "Passoword user max 20")
		@Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "Password user invalid")
		String passwordUser
) {}