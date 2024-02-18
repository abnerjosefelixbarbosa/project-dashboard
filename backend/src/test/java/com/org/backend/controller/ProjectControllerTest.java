package com.org.backend.controller;

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
		User user = new User();
		user.setName("name1");
		user.setEmail("email1@gmail.com");
		user.setPassword("@Password1");
		user.setDateBirth(Date.from(calendarBirth.toInstant()));
		Account account = new Account();
		account.setUser(user);

		userRepository.save(user);
		account = accountRepository.save(account);

		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(Calendar.YEAR, localDate.getYear());
		calendarStart.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth() + 10);
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(Calendar.YEAR, localDate.getYear());
		calendarEnd.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth() + 11);
		String name = "name111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		String description = "description111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		CreateProjectRequest request = new CreateProjectRequest(name, description, Date.from(calendarStart.toInstant()),
				Date.from(calendarEnd.toInstant()), BigDecimal.valueOf(0.01), account.getId());
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
		User user = new User();
		user.setName("name1");
		user.setEmail("email1@gmail.com");
		user.setPassword("@Password1");
		user.setDateBirth(Date.from(calendarBirth.toInstant()));
		Account account = new Account();
		account.setUser(user);
		Project project = new Project();
		project.setName("name1");
		project.setDescription("description1");
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(Calendar.YEAR, localDate.getYear());
		calendarStart.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth() + 10);
		project.setDateStart(Date.from(calendarStart.toInstant()));
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.set(Calendar.YEAR, localDate.getYear() + 10);
		calendarEnd.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth() + 10);
		project.setDateEnd(Date.from(calendarEnd.toInstant()));
		project.setBudget(BigDecimal.valueOf(0.01));
		
		userRepository.save(user);
		account = accountRepository.save(account);
		project.setAccount(account);
		project = projectRepository.save(project);
	
		Calendar calendarStart1 = Calendar.getInstance();
		calendarStart1.set(Calendar.YEAR, localDate.getYear());
		calendarStart1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarStart1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth() + 10);
		Calendar calendarEnd1 = Calendar.getInstance();
		calendarEnd1.set(Calendar.YEAR, localDate.getYear());
		calendarEnd1.set(Calendar.MONTH, localDate.getMonthValue() - 1);
		calendarEnd1.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth() + 11);
		String name = "name111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		String description = "description111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		UpdateProjectRequest request = new UpdateProjectRequest(name, description, Date.from(calendarStart1.toInstant()),
				Date.from(calendarEnd1.toInstant()), BigDecimal.valueOf(0.01));
		String obj = objectMapper.writeValueAsString(request);
		
		mockMvc.perform(patch("/api/projects/update?id=" + project.getId()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(obj)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
}
