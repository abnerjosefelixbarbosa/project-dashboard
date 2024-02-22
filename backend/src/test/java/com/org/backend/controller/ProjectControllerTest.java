package com.org.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.math.BigDecimal;
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
@ContextConfiguration
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
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarBirth.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarBirth.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		User user = new User(null, "name1", "email1@gmail.com", "@Password1", Date.from(calendarBirth.toInstant()),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);

		userRepository.save(user);
		account = accountRepository.save(account);

		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(Calendar.YEAR, localDate.getYear() + 1);
		calendarStart.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(Calendar.YEAR, localDate.getYear() + 2);
		calendarEnd.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		CreateProjectRequest request = new CreateProjectRequest("name1", "description1",
				Date.from(calendarStart.toInstant()), Date.from(calendarEnd.toInstant()), BigDecimal.valueOf(0.01),
				account.getId());
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/projects/create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}

	@Test
	public void shouldUpdateProjectByIdAndReturn200Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarBirth.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarBirth.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(Calendar.YEAR, localDate.getYear() + 1);
		calendarStart.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(Calendar.YEAR, localDate.getYear() + 2);
		calendarEnd.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		User user = new User(null, "name1", "email1@gmail.com", "@Password1", Date.from(calendarBirth.toInstant()),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);
		Project project = new Project(null, "name1", "description1", Date.from(calendarStart.toInstant()),
				Date.from(calendarEnd.toInstant()), BigDecimal.valueOf(0.01), null);

		userRepository.save(user);
		account = accountRepository.save(account);
		project.setAccount(account);
		project = projectRepository.save(project);

		Calendar calendarStart1 = Calendar.getInstance();
		calendarStart1.set(Calendar.YEAR, localDate.getYear() + 1);
		calendarStart1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarEnd1 = Calendar.getInstance();
		calendarEnd1.set(Calendar.YEAR, localDate.getYear() + 2);
		calendarEnd1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		UpdateProjectRequest request = new UpdateProjectRequest("name2", "description2",
				Date.from(calendarStart1.toInstant()), Date.from(calendarEnd1.toInstant()), BigDecimal.valueOf(0.01));
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(patch("/api/projects/update-by-id?id=" + project.getId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	@Test
	public void shouldDeleteProjectByIdAndReturn204Status() throws Exception {
		LocalDate localDate = LocalDate.now();
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarBirth.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarBirth.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(Calendar.YEAR, localDate.getYear() + 1);
		calendarStart.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(Calendar.YEAR, localDate.getYear() + 2);
		calendarEnd.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		User user = new User(null, "name1", "email1@gmail.com", "@Password1", Date.from(calendarBirth.toInstant()),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);
		Project project = new Project(null, "name1", "description1", Date.from(calendarStart.toInstant()),
				Date.from(calendarEnd.toInstant()), BigDecimal.valueOf(0.01), null);

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
		Calendar calendarBirth = Calendar.getInstance();
		calendarBirth.set(Calendar.YEAR, localDate.getYear() - 25);
		calendarBirth.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarBirth.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarStart1 = Calendar.getInstance();
		calendarStart1.set(Calendar.YEAR, localDate.getYear() + 1);
		calendarStart1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarEnd1 = Calendar.getInstance();
		calendarEnd1.set(Calendar.YEAR, localDate.getYear() + 2);
		calendarEnd1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarStart2 = Calendar.getInstance();
		calendarStart2.set(Calendar.YEAR, localDate.getYear() + 2);
		calendarStart2.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart2.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		Calendar calendarEnd2 = Calendar.getInstance();
		calendarEnd2.set(Calendar.YEAR, localDate.getYear() + 3);
		calendarEnd2.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd2.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
		User user = new User(null, "name1", "email1@gmail.com", "@Password1", Date.from(calendarBirth.toInstant()),
				null);
		Account account = new Account(null, new Date(), Level.BASIC, user, null);
		Project project1 = new Project(null, "name1", "description1", Date.from(calendarStart1.toInstant()),
				Date.from(calendarEnd1.toInstant()), BigDecimal.valueOf(0.01), null);
		Project project2 = new Project(null, "name2", "description2", Date.from(calendarStart2.toInstant()),
				Date.from(calendarEnd2.toInstant()), BigDecimal.valueOf(0.50), null);

		userRepository.save(user);
		account = accountRepository.save(account);
		project1.setAccount(account);
		projectRepository.save(project1);
		project2.setAccount(account);
		projectRepository.save(project2);

		mockMvc.perform(get("/api/projects/list-all-by-account-id?accountId=" + account.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
}
