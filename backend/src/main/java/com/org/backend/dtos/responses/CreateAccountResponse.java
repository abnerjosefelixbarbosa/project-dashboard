package com.org.backend.dtos.responses;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.backend.entities.Account;
import com.org.backend.entities.Level;

public record CreateAccountResponse(String id, @JsonFormat(pattern = "yyyy-MM-dd HH:mm") Date dateCreation, Level level, String idUser, String nameUser,
		String emailUser, String passwordUser, @JsonFormat(pattern = "yyyy-MM-dd") Date dateBirthUser) {
	public CreateAccountResponse(Account account) {
		this(account.getId(), account.getDateCreation(), account.getLevel(), account.getUser().getId(),
				account.getUser().getName(), account.getUser().getEmail(), account.getUser().getPassword(),
				account.getUser().getDateBirth());
	}
}