package com.org.backend.dtos.requests;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginAccountRequest(
		@NotNull(message = "email user required") @Email(message = "email user invalid") @Length(min = 1, message = "email user at min 1") String emailUser,
		@NotNull(message = "password user required") @Length(min = 1, max = 20, message = "passoword user at min 1 and max 20") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "password user invalid") String passwordUser) {
}