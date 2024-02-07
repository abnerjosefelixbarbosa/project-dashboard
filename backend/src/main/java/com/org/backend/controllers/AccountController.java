package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dto.request.AccountRequest;
import com.org.backend.dto.response.AccountResponse;
import com.org.backend.entities.Account;
import com.org.backend.interfaces.IAccount;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/accounts")
public class AccountController {
	@Autowired
	private IAccount iAccount;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
		Account accountResponse = iAccount.createAccount(new Account(accountRequest));
		return ResponseEntity.status(201).body(new AccountResponse(accountResponse));
	}
}
