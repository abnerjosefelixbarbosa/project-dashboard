package com.org.backend.dtos.requests;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginAccountRequest(
		@NotNull(message = "Email user null") @Email(message = "Email user invalid") @Length(min = 1, message = "Email user at min 1") String emailUser,
		@NotNull(message = "Password user null") @Length(min = 1, max = 20, message = "Passoword user at min 1 and max 20") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "Password user pattern") String passwordUser) {
}