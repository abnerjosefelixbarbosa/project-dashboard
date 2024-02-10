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

import com.org.backend.dto.request.CreateAccountRequest;
import com.org.backend.dto.request.LoginAccountRequest;
import com.org.backend.dto.response.CreateAccountResponse;
import com.org.backend.dto.response.LoginAccountResponse;
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
	public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
		Account response = iAccount.createAccount(new Account(request));
		return ResponseEntity.status(201).body(new CreateAccountResponse(response));
	}
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LoginAccountResponse> createAccount(@RequestBody @Valid LoginAccountRequest request) {
		//Account response = iAccount.createAccount(new Account(request));
		return ResponseEntity.status(201).body(null);
	}
}
