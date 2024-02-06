package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.Account;
import com.org.backend.interfaces.IAccount;
import com.org.backend.interfaces.IUser;
import com.org.backend.repositories.AccountRepository;

@Service
public class AccountService implements IAccount {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private IUser iUser;
	
	public Account createAccount(Account account) {
		iUser.save(account.getUser());
		return accountRepository.save(account);
	}
}
