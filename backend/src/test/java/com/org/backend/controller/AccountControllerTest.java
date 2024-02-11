package com.org.backend.controller;

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
import com.org.backend.dto.request.CreateAccountRequest;
import com.org.backend.dto.request.LoginAccountRequest;
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
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1999);
		calendar.set(Calendar.MONTH, 04);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendar.toInstant()));
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}

	@Test
	public void shouldCreateAccountWithNameNullAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest(null, "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithNameLength0AndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("", "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithNameLength101AndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest(
				"name111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111",
				"email1@gmail.com", "@Password1", Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithEmailNullAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", null, "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithEmailLength0AndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithEmailInvalidAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithPasswordNullAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", null,
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithPasswordLength0AndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithPasswordLength20AndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com",
				"@Password1111111111111111111111111111111111111111111111111111111111111111",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithPasswordNoPatternAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithDateBirthNullAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1", null);
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithDateBirthPresentAndReturn400Status() throws Exception {
		LocalDate currentDate = LocalDate.now();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, currentDate.getYear());
		calendar1.set(Calendar.MONTH, currentDate.getMonthValue());
		calendar1.set(Calendar.DAY_OF_MONTH, currentDate.getDayOfMonth());
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithDateBirthFutureAndReturn400Status() throws Exception {
		LocalDate currentDate = LocalDate.now();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, currentDate.getYear() + 1);
		calendar1.set(Calendar.MONTH, currentDate.getMonthValue());
		calendar1.set(Calendar.DAY_OF_MONTH, currentDate.getDayOfMonth());
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithEmailExistingAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1));

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, 2005);
		calendar2.set(Calendar.MONTH, 10);
		calendar2.set(Calendar.DAY_OF_MONTH, 15);
		CreateAccountRequest request2 = new CreateAccountRequest("name2", "email1@gmail.com", "@Password2",
				Date.from(calendar2.toInstant()));
		String obj2 = objectMapper.writeValueAsString(request2);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj2))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("email exist")).andDo(print());
	}

	@Test
	public void shouldCreateAccountWithPasswordExistingAndReturn400Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1));

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, 2005);
		calendar2.set(Calendar.MONTH, 10);
		calendar2.set(Calendar.DAY_OF_MONTH, 15);
		CreateAccountRequest request2 = new CreateAccountRequest("name2", "email2@gmail.com", "@Password1",
				Date.from(calendar2.toInstant()));
		String obj2 = objectMapper.writeValueAsString(request2);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj2))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("password exist")).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithEmailNullAndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest(null, "@Password1");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithEmaiLength0AndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("", "@Password1");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithEmailInvalidAndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("email1gmail.com", "@Password1");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithPasswordNullAndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("email1@gmail.com", null);
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithPasswordLength0AndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("email1@gmail.com", "");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithPasswordLength21AndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("email1@gmail.com", "@Password111111111111");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithPasswordNoPatternAndReturn400Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("email1@gmail.com", "password1");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(print());
	}

	@Test
	public void shouldLoginAccountWithEmailAndPasswordNotFindAndReturn404Status() throws Exception {
		LoginAccountRequest request = new LoginAccountRequest("email1@gmail.com", "@Password1");
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.exception").value("email and password not find"))
				.andDo(print());
	}

	@Test
	public void shouldLoginAccountAndReturn200Status() throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1999);
		calendar.set(Calendar.MONTH, 04);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = new CreateAccountRequest("name1", "email1@gmail.com", "@Password1",
				Date.from(calendar.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1));

		LoginAccountRequest request2 = new LoginAccountRequest("email1@gmail.com", "@Password1");
		String obj2 = objectMapper.writeValueAsString(request2);

		mockMvc.perform(post("/api/accounts/login").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj2)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
}
