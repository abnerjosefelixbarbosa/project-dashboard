package com.org.backend.dtos.requests;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginAccountRequest(
		@NotNull(message = "email user invalid") @Email(message = "email user invalid") @Length(min = 1, message = "email user invalid") String emailUser,
		@NotNull(message = "password user invalid") @Length(min = 1, max = 20, message = "passoword user invalid") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "password user invalid") String passwordUser) {
}