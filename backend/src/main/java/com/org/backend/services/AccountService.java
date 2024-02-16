package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.Account;
import com.org.backend.entities.User;
import com.org.backend.exception.BusinessException;
import com.org.backend.exception.NotFoundException;
import com.org.backend.exception.ValidationParamException;
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
					throw new NotFoundException("email user and password user not found");
				});
	}
	
	public Account updateAccount(String id, Account account) {
		validateParamId(id);
		validateUpdateAccount(account.getUser());
		Account findById = findAccountById(id);
		
		findById.setLevel(account.getLevel());
		findById.getUser().setDateBirth(account.getUser().getDateBirth());
		findById.getUser().setEmail(account.getUser().getEmail());
		findById.getUser().setName(account.getUser().getName());
		findById.getUser().setPassword(account.getUser().getPassword());
		
		iUser.save(findById.getUser());
		return accountRepository.save(findById);
	}
	
	public Account findAccountById(String id) {
		return accountRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("account id not found");
		});
	}
	
	private void validateParamId(String id) {
		if (id == null) {
			throw new ValidationParamException("id invalid");
		}
	}

	private void validateCreateAccount(User user) {
		if (accountRepository.existsByUserEmail(user.getEmail())) {
			throw new BusinessException("email user exist");
		}
		if (accountRepository.existsByUserPassword(user.getPassword())) {
			throw new BusinessException("password user exist");
		}
	}
	
	private void validateUpdateAccount(User user) {
		if (accountRepository.existsByUserEmail(user.getEmail())) {
			throw new BusinessException("email user exist");
		}
		if (accountRepository.existsByUserPassword(user.getPassword())) {
			throw new BusinessException("password user exist");
		}
	}
}
