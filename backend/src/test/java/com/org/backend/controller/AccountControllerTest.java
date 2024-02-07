package com.org.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.backend.dto.request.AccountRequest;
import com.org.backend.repositories.AccountRepository;
import com.org.backend.repositories.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
public class AccountControllerTest {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setup() {
		accountRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@AfterEach
	public void tearDown() {
		accountRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void shouldCreateAccountAndReturn201Status() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1999);
		calendar.set(Calendar.MONTH, 04);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		AccountRequest accountRequest = buildAccountRequest("nome1", "email1@gmail.com", "@Password1",
				Date.from(calendar.toInstant()));
		String obj = objectMapper.writeValueAsString(accountRequest);

		mockMvc.perform(post("/api/accounts").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(obj)).andExpect(MockMvcResultMatchers.status().isCreated()).andDo(print());
	}

	private AccountRequest buildAccountRequest(String name, String email, String password, Date date) {
		return new AccountRequest(name, email, password, date);
	}
}
