package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dtos.requests.EmailRequest;
import com.org.backend.entities.Email;
import com.org.backend.interfaces.IAccount;
import com.org.backend.services.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/emails")
public class EmailController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private IAccount iAccount;

	@PostMapping("/send")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void>  sendEmail(@RequestBody @Valid EmailRequest request) {
		var email = new Email(request);
		iAccount.findByUserEmail(request.email());
		email.setBody("test");
		emailService.sendEmail(email);
		return ResponseEntity.status(204).body(null);
	}
}
