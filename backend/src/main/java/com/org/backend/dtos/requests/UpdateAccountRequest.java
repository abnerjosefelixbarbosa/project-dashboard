package com.org.backend.dtos.requests;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.org.backend.entities.Level;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record UpdateAccountRequest(
		@NotNull(message = "level null") Level level,
		@Length(min = 1, max = 100, message = "name user at min 1 and max 100") @NotNull(message = "name user null") String nameUser,
		@Email(message = "email user invalid") @NotNull(message = "email user null") @Length(min = 1, message = "email user at min 1") String emailUser,
		@Length(min = 1, max = 20, message = "password user at min 1 and max 20") @NotNull(message = "password user null") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$", message = "password user invalid") String passwordUser,
		@NotNull(message = "date birth user null") @Past(message = "date birth user invalid") Date dateBirthUser) {
}