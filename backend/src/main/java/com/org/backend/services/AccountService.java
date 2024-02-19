package com.org.backend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.Account;
import com.org.backend.entities.Level;
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
		validateSaveAccount(account.getUser());
		account.setLevel(Level.BASIC);
		account.setDateCreation(new Date());
		iUser.save(account.getUser());
		return accountRepository.save(account);
	}

	public Account loginAccount(Account account) {
		String userEmail = account.getUser().getEmail();
		String userPassword = account.getUser().getPassword();
		return accountRepository.findByUserEmailAndUserPassword(userEmail, userPassword).orElseThrow(() -> {
			throw new NotFoundException("email user and password user not found");
		});
	}

	@Transactional
	public Account updateAccount(String id, Account account) {
		validateSaveAccount(account.getUser());
		Account findAccountById = findAccountById(id);
		findAccountById.update(account);
		iUser.save(findAccountById.getUser());
		return accountRepository.save(findAccountById);
	}

	public Account findAccountById(String id) {
		return accountRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("account id not found");
		});
	}

	private void validateSaveAccount(User user) {
		Date date = new Date();
		if (user.getDateBirth().toString().equals(date.toString())) {
			throw new BusinessException("user date birth invalid");
		}
		if (accountRepository.existsByUserEmail(user.getEmail())) {
			throw new BusinessException("user email invalid");
		}
		if (accountRepository.existsByUserPassword(user.getPassword())) {
			throw new BusinessException("user password invalid");
		}
	}
}
