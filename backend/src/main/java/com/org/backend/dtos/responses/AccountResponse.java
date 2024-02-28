package com.org.backend.dtos.responses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.backend.entities.Account;
import com.org.backend.entities.Level;

public record AccountResponse(
	String id,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date dateCreation,
	Level level,
	UserResponse userResponse
) {
	public AccountResponse(Account account) {
		this(
		    account.getId(),
			account.getDateCreation(),
			account.getLevel(),
			new UserResponse(account.getUser())
		);
	}
}