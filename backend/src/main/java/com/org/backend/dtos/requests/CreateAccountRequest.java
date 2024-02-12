package com.org.backend.dtos.requests;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequest(
		@Length(min = 1, max = 100, message = "name user invalid") @NotNull(message = "name user invalid") String nameUser,
		@Email(message = "email user invalid") @NotNull(message = "email user invalid") @Length(min = 1, message = "email user invalid") String emailUser,
		@Length(min = 1, max = 20, message = "password user invalid") @NotNull(message = "password user invalid") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "password user invalid") String passwordUser,
		@NotNull(message = "date birth user invalid") @Past(message = "date birth user invalid") Date dateBirthUser) {
}