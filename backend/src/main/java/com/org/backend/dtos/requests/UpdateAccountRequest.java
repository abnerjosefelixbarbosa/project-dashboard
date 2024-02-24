package com.org.backend.dtos.requests;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.org.backend.entities.Level;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record UpdateAccountRequest(
		@NotNull(message = "Level null") Level level,
		@Length(min = 1, max = 100, message = "Name user at min 1 and max 100") @NotNull(message = "Name user null") String nameUser,
		@Email(message = "Email user invalid") @NotNull(message = "Email user null") @Length(min = 1, message = "Email user at min 1") String emailUser,
		@Length(min = 1, max = 20, message = "Password user at min 1 and max 20") @NotNull(message = "Password user null") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "Password user pattern") String passwordUser,
		@NotNull(message = "Date birth user null") @Past(message = "Date birth user past") LocalDate dateBirthUser) {
}