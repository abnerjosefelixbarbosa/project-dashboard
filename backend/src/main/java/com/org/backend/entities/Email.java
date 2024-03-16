package com.org.backend.entities;

import com.org.backend.dtos.requests.EmailRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	private String to;
	private String subject;
	private String body;
	
	public Email(EmailRequest request) {
		this.to = request.email();
		this.subject = "title";
		this.body = "test";
	} 
}
