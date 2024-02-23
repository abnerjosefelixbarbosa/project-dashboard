package com.org.backend.dtos.requests;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record CreateAccountRequest(
		@Length(min = 1, max = 100, message = "Name user must have at min 1 and max 100 characters") @NotNull(message = "Name user required") String nameUser,
		@Email(message = "Email user invalid") @NotNull(message = "Email user required") @Length(min = 1, message = "Email user must have at min 1 character") String emailUser,
		@Length(min = 1, max = 20, message = "Password user at min 1 and max 20 characters") @NotNull(message = "Password user required") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "Password user must have 1 special character and 1 uppercase letter") String passwordUser,
		@NotNull(message = "Date birth user required") @Past(message = "Date birth user different from past date") LocalDate dateBirthUser) {
}