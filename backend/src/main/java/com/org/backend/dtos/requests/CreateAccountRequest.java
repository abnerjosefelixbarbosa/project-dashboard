package com.org.backend.dtos.requests;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequest(
		@Length(max = 100, message = "Name user max 100")
		@NotNull(message = "Name user null")
		@NotEmpty(message = "Name user empty")
		String nameUser,
		@Email(message = "Email user invalid")
		@NotNull(message = "Email user null")
		@NotEmpty(message = "Email user empty")
		String emailUser,
		@NotEmpty(message = "Password user empty")
		@Length(max = 20, message = "Password user max 20") 
		@NotNull(message = "Password user null")
		@Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "Password user invalid")
		String passwordUser,
		@NotNull(message = "Date birth user null")
		@Past(message = "Date birth user past")
		LocalDate dateBirthUser
) {}