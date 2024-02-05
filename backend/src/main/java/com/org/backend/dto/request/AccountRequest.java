package com.org.backend.dto.request;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(
	@Length(min = 1, max = 100, message = "name invalid")
	@NotNull(message = "name invalid")
    String name,
    @Email(message = "email invalid")
	@NotNull(message = "email invalid")
	String email,
	@Length(min = 1, max = 20, message = "password invalid")
	@NotNull(message = "password invalid")
	String password,
	@NotNull(message = "date_birth invalid")
	Date date_birth
) {

}
