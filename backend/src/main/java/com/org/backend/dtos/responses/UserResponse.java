package com.org.backend.dtos.responses;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.backend.entities.User;

public record UserResponse(
	String id,
	String name,
	String email,
	String password,
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate dateBirth
) {
	public UserResponse(User user) {
		this(
			user.getId(),
			user.getName(),
			user.getEmail(),
			user.getPassword(),
			user.getDateBirth()
		);
	}
}