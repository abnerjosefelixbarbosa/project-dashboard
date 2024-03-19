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
	    var format = String.format(
	    		"" +
	    		"..." +
	    		"\n" +		
	            "click no link para alterar senha" +
	            "\n" +
	            "..." +
	            "\n" +
	    		"http://localhost:5173/update-password?email=%s" +
	    	    ""			
	    , request.email());
		email.setBody(format);
		emailService.sendEmail(email);
		return ResponseEntity.status(204).body(null);
	}
}
