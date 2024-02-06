package com.org.backend.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.backend.entities.Account;
import com.org.backend.entities.Level;

public record AccountResponse(String id, Date dateCreation, Level level, String idUser, String nameUser,
		String emailUser, String passwordUser, @JsonFormat(pattern = "yyyy-MM-dd") Date dateBirthUser) {
	public AccountResponse(Account account) {
		this(account.getId(), account.getDateCreation(), account.getLevel(), account.getUser().getId(),
				account.getUser().getName(), account.getUser().getEmail(), account.getUser().getPassword(),
				account.getUser().getDateBirth());
	}
}