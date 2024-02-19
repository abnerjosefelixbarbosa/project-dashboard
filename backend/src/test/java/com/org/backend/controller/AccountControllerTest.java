package com.org.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
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
import com.org.backend.dtos.requests.CreateAccountRequest;
import com.org.backend.dtos.requests.LoginAccountRequest;
import com.org.backend.dtos.requests.UpdateAccountRequest;
import com.org.backend.entities.Account;
import com.org.backend.entities.Level;
import com.org.backend.entities.User;
import com.org.backend.repositories.AccountRepository;
import com.org.backend.repositories.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
public class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;

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
		LocalDate localDate = LocalDate.now();
		Calendar calendarBirthUser = Calendar.getInstance();
		calendarBirthUser.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarBirthUser.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarBirthUser.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		CreateAccountRequest request = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendarBirthUser.toInstant()));
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}

	@Test
	public void shouldLoginAccountAndReturn200Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		Calendar calendarDateBith = Calendar.getInstance();
		calendarDateBith.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarDateBith.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarDateBith.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		User user = new User(null, "name1", "email1@gmail.com", "@Password1", Date.from(calendarDateBith.toInstant()), null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);

		userRepository.save(user);
		accountRepository.save(account);

		LoginAccountRequest request = new LoginAccountRequest("email1@gmail.com", "@Password1");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	@Test
	public void shouldUpdateAccountAndReturn200Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		Calendar calendarDateBith = Calendar.getInstance();
		calendarDateBith.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarDateBith.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarDateBith.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		User user = new User(null, "name1", "email1@gmail.com", "@Password1", Date.from(calendarDateBith.toInstant()), null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);

		userRepository.save(user);
		account = accountRepository.save(account);

		Calendar calendarDateBith1 = Calendar.getInstance();
		calendarDateBith1.set(Calendar.YEAR, localDate.getYear() - 20);
		calendarDateBith1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarDateBith1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		UpdateAccountRequest request = new UpdateAccountRequest(Level.FULL, "name2", "email2@gmail.com", "@Password2",
				Date.from(calendarDateBith1.toInstant()));
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(patch("/api/accounts/update?id=" + account.getId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

}
