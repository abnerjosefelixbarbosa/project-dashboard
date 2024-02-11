package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.Account;
import com.org.backend.entities.User;
import com.org.backend.exception.BusinessException;
import com.org.backend.exception.NotFoundException;
import com.org.backend.interfaces.IAccount;
import com.org.backend.interfaces.IUser;
import com.org.backend.repositories.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService implements IAccount {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private IUser iUser;

	@Transactional
	public Account createAccount(Account account) {
		validateCreateAccount(account.getUser());
		iUser.save(account.getUser());
		return accountRepository.save(account);
	}

	public Account loginAccount(Account account) {
		return accountRepository
				.findByUserEmailAndUserPassword(account.getUser().getEmail(), account.getUser().getPassword())
				.orElseThrow(() -> {
					throw new NotFoundException("email and password not find");
				});
	}

	private void validateCreateAccount(User user) {
		if (accountRepository.existsByUserEmail(user.getEmail())) {
			throw new BusinessException("email exist");
		}
		if (accountRepository.existsByUserPassword(user.getPassword())) {
			throw new BusinessException("password exist");
		}
	}
}
