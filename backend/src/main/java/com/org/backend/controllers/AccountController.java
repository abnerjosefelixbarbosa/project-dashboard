package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.org.backend.dtos.responses.CreateAccountResponse;
import com.org.backend.dtos.responses.LoginAccountResponse;
import com.org.backend.dtos.responses.UpdadeAccountResponse;
import com.org.backend.entities.Account;
import com.org.backend.exception.ValidationParamException;
import com.org.backend.interfaces.IAccount;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
	private IAccount iAccount;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody @Valid CreateAccountRequest request) {
		Account response = iAccount.createAccount(new Account(request));
		return ResponseEntity.status(201).body(new CreateAccountResponse(response));
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LoginAccountResponse> loginAccount(@RequestBody @Valid LoginAccountRequest request) {
		Account response = iAccount.loginAccount(new Account(request));
		return ResponseEntity.status(200).body(new LoginAccountResponse(response));
	}

	@PatchMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UpdadeAccountResponse> updateAccount(@RequestParam(required = false) String id,
			@RequestBody @Valid UpdateAccountRequest request) {
		if (id == null) {
			throw new ValidationParamException("id invalid");
		}
		Account response = iAccount.updateAccount(id, new Account(request));
		return ResponseEntity.status(200).body(new UpdadeAccountResponse(response));
	}
}
