package com.org.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
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

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
public class AccountControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Disabled
	public void shouldCreateAccountAndReturn201Status() throws Exception {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, 1999);
		calendar1.set(Calendar.MONTH, 04);
		calendar1.set(Calendar.DAY_OF_MONTH, 20);
		CreateAccountRequest request1 = buildAccountRequest("nome1", "email1@gmail.com", "@Password1",
				Date.from(calendar1.toInstant()));
		String obj1 = objectMapper.writeValueAsString(request1);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, 2005);
		calendar2.set(Calendar.MONTH, 10);
		calendar2.set(Calendar.DAY_OF_MONTH, 15);
		CreateAccountRequest request2 = buildAccountRequest("nome2", "email2@gmail.com", "@Password2",
				Date.from(calendar2.toInstant()));
		String obj2 = objectMapper.writeValueAsString(request2);
		

		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj1)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
		mockMvc.perform(post("/api/accounts/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj2)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}

	private CreateAccountRequest buildAccountRequest(String nameUser, String emailUser, String passwordUser,
			Date dateBirthUser) {
		return new CreateAccountRequest(nameUser, emailUser, passwordUser, dateBirthUser);
	}
}
