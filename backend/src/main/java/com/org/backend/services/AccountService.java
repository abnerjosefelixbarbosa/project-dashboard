package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		validateAccount(account.getUser());
		account.setLevel(Level.BASIC);
		var encoder = crypt().encode(account.getPassword());
		account.getUser().setPassword(encoder);
		iUser.save(account.getUser());
		return accountRepository.save(account);
	}

	public Account loginAccount(Account account) {
		var userEmail = account.getUser().getEmail();
		var userPassword = account.getUser().getPassword();
		return accountRepository.findByUserEmailOrUserPassword(userEmail, userPassword).orElseThrow(() -> {
			throw new NotFoundException("Email user or password user not found");
		});
	}

	@Transactional
	public Account updateAccountById(String id, Account account) {
		validateAccount(account.getUser());
		var findAccountById = findAccountById(id);
		findAccountById.update(account);
		iUser.save(findAccountById.getUser());
		return accountRepository.save(findAccountById);
	}

	public Account findAccountById(String id) {
		return accountRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("Account id not found");
		});
	}
	
	public Account findByUserEmail(String email) {
		return accountRepository.findByUserEmail(email).orElseThrow(() -> {
			throw new NotFoundException("Email not found");
		});
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return accountRepository.findByUserEmail(email).orElseThrow(() -> {
			throw new UsernameNotFoundException("Email user not found");
		});
	}
	
	@Transactional
	public Account updatePasswordByEmail(String email, Account account) {
		validadePassword(account.getUser());
		var findByUserEmail =  findByUserEmail(email);
		findByUserEmail.updatePasswordByEmail(account);
		var encoder = crypt().encode(findByUserEmail.getPassword());
		findByUserEmail.getUser().setPassword(encoder);
		iUser.save(findByUserEmail.getUser());
		return accountRepository.save(findByUserEmail);
	}
	
	private void validadePassword(User user) {
		var stream = accountRepository.findAll().parallelStream();
		stream.anyMatch((val) -> {
			if (crypt().matches(user.getPassword(), val.getPassword())) {
				throw new BusinessException("Password user exist");
			}
			return false;
		});
	}
	
	private void validateAccount(User user) {
		if (accountRepository.existsByUserEmail(user.getEmail())) {
			throw new BusinessException("Email user exist");
		}
		var stream = accountRepository.findAll().parallelStream();
		stream.anyMatch((val) -> {
			if (crypt().matches(user.getPassword(), val.getPassword())) {
				throw new BusinessException("Password user exist");
			}
			return false;
		});
	}
	
	private BCryptPasswordEncoder crypt() {
		return new BCryptPasswordEncoder();
	}
}
