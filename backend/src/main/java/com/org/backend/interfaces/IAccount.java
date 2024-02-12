package com.org.backend.interfaces;

import com.org.backend.entities.Account;

public interface IAccount {
	Account createAccount(Account account);
	Account loginAccount(Account account);
	Account updateAccount(String id, Account account);
}
