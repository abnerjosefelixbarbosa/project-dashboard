package com.org.backend.dto.response;

import java.util.Date;

import com.org.backend.entities.Level;

public record AccountResponse(
	String id,
	Date date_creation,
	Level level,
    String idUser,
    String nameUser,
	String emailUser,
	String passwordUser,
	Date date_birthUser
) {

}