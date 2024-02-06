package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dto.request.AccountRequest;
import com.org.backend.dto.response.AccountResponse;
import com.org.backend.entities.Account;
import com.org.backend.interfaces.IAccount;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	private IAccount iAccount;
	
	@PostMapping
	public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
		Account accountResponse = iAccount.createAccount(new Account(accountRequest));
		return ResponseEntity.status(201).body(new AccountResponse(accountResponse));
	}
}
