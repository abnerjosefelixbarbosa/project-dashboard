package com.org.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dto.request.AccountRequest;
import com.org.backend.dto.response.AccountResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@PostMapping
	public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
		return ResponseEntity.status(201).body(null);
	}
}
