package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dtos.requests.CreateAccountRequest;
import com.org.backend.dtos.requests.LoginAccountRequest;
import com.org.backend.dtos.requests.UpdateAccountRequest;
import com.org.backend.dtos.responses.AccountResponse;
import com.org.backend.dtos.responses.AccountResponseLogin;
import com.org.backend.entities.Account;
import com.org.backend.exception.ValidationParamException;
import com.org.backend.interfaces.IAccount;
import com.org.backend.interfaces.IToken;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	private IAccount iAccount;
	@Autowired
	private IToken iToken;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
		Account response = iAccount.createAccount(new Account(request));
		return ResponseEntity.status(201).body(new AccountResponse(response));
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AccountResponseLogin> loginAccount(@RequestBody @Valid LoginAccountRequest request) {
		UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(request.emailUser(), request.passwordUser());
		Authentication auth = authenticationManager.authenticate(user);
		Account response = (Account) iAccount.loadUserByUsername(request.emailUser());
		String token = iToken.generateToken((Account) auth.getPrincipal());
		return ResponseEntity.status(200).body(new AccountResponseLogin(response, token));
	}

	@PatchMapping("/update-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AccountResponse> updateAccountById(@RequestParam(required = false) String id,
			@RequestBody @Valid UpdateAccountRequest request) {
		if (id == null) {
			throw new ValidationParamException("Param id null");
		}
		Account response = iAccount.updateAccountById(id, new Account(request));
		return ResponseEntity.status(200).body(new AccountResponse(response));
	}
	
	@GetMapping("/find-by-id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AccountResponse> findAccountById(@RequestParam(required =  false) String id) {
		if (id == null) {
			throw new ValidationParamException("Param id null");
		}
		Account response = iAccount.findAccountById(id);
		return ResponseEntity.status(200).body(new AccountResponse(response));
	}
}
