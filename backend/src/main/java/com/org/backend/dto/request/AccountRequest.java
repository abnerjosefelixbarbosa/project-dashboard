package com.org.backend.dto.request;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record AccountRequest(
		@Length(min = 1, max = 100, message = "name invalid") @NotNull(message = "name invalid") String nameUser,
		@Email(message = "email invalid") @NotNull(message = "email invalid") String emailUser,
		@Length(min = 1, max = 20, message = "password invalid") @NotNull(message = "password invalid") 
		@Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "password user invalid") String passwordUser,
		@NotNull(message = "date birth invalid") @Past(message = "date birth invalid") Date dateBirthUser) {
}