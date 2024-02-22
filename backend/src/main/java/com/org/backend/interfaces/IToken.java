package com.org.backend.interfaces;

import com.org.backend.entities.Account;

public interface IToken {
	public String generateToken(Account account);
	public String validateToken(String token);
}
