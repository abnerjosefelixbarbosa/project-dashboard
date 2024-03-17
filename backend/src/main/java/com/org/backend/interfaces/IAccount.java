package com.org.backend.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.org.backend.entities.Account;

public interface IAccount extends UserDetailsService {
	Account createAccount(Account account);
	Account loginAccount(Account account);
	Account updateAccountById(String id, Account account);
	Account findAccountById(String id);
	Account findByUserEmail(String email);
	Account updatePasswordByEmail(String email, Account account);
}
