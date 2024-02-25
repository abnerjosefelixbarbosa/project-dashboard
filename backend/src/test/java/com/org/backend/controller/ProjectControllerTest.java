package com.org.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.backend.dtos.requests.CreateProjectRequest;
import com.org.backend.dtos.requests.UpdateProjectRequest;
import com.org.backend.entities.Account;
import com.org.backend.entities.Level;
import com.org.backend.entities.Project;
import com.org.backend.entities.User;
import com.org.backend.repositories.AccountRepository;
import com.org.backend.repositories.ProjectRepository;
import com.org.backend.repositories.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProjectControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ProjectRepository projectRepository;

	@BeforeEach
	public void setup() {
		projectRepository.deleteAll();
		accountRepository.deleteAll();
		userRepository.deleteAll();
	}

	@AfterEach
	public void tearDown() {
		projectRepository.deleteAll();
		accountRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	public void shouldCreateProjectAndReturn201Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		User user = new User(null, "name1", "email1@gmail.com", crypt().encode("@Password1"), localDate.withYear(1999),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);

		userRepository.save(user);
		account = accountRepository.save(account);

		CreateProjectRequest request = new CreateProjectRequest("name1", "description1", localDate.plusYears(1),
				localDate.plusYears(2), BigDecimal.valueOf(0.01), account.getId());
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/projects/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}

	@Test
	public void shouldUpdateProjectByIdAndReturn200Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		User user = new User(null, "name1", "email1@gmail.com", crypt().encode("@Password1"), localDate.withYear(1999),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);
		Project project = new Project(null, "name1", "description1", localDate.plusYears(1), localDate.plusYears(2),
				BigDecimal.valueOf(0.01), null);

		userRepository.save(user);
		account = accountRepository.save(account);
		project.setAccount(account);
		project = projectRepository.save(project);

		UpdateProjectRequest request = new UpdateProjectRequest("name1", "description1", localDate.plusYears(1),
				localDate.plusYears(2), BigDecimal.valueOf(0.02));
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(patch("/api/projects/update-by-id?id=" + project.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(obj))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}

	@Test
	public void shouldDeleteProjectByIdAndReturn204Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		User user = new User(null, "name1", "email1@gmail.com", crypt().encode("@Password1"), localDate.withYear(1999),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);
		Project project = new Project(null, "name1", "description1", localDate.plusYears(1), localDate.plusYears(2),
				BigDecimal.valueOf(0.01), null);

		userRepository.save(user);
		account = accountRepository.save(account);
		project.setAccount(account);
		project = projectRepository.save(project);

		mockMvc.perform(delete("/api/projects/delete-by-id?id=" + project.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent()).andDo(print());
	}

	@Test
	public void shouldListAllProjectByAccountIdAndReturn200Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		User user = new User(null, "name1", "email1@gmail.com", crypt().encode("@Password1"), localDate.withYear(1999),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);
		Project project1 = new Project(null, "name1", "description1", localDate.plusYears(1), localDate.plusYears(2),
				BigDecimal.valueOf(0.01), null);
		Project project2 = new Project(null, "name2", "description2", localDate.plusYears(3), localDate.plusYears(4),
				BigDecimal.valueOf(0.50), null);

		userRepository.save(user);
		account = accountRepository.save(account);
		project1.setAccount(account);
		projectRepository.save(project1);
		project2.setAccount(account);
		projectRepository.save(project2);

		mockMvc.perform(get("/api/projects/list-all-by-account-id?accountId=" + account.getId())
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}

	private BCryptPasswordEncoder crypt() {
		return new BCryptPasswordEncoder();
	}
}
