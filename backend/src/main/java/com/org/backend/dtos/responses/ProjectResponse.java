package com.org.backend.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.backend.entities.Level;
import com.org.backend.entities.Project;

public record ProjectResponse(String id, String name, String description,
		@JsonFormat(pattern = "yyyy-MM-dd") LocalDate dateStart, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dateEnd,
		BigDecimal budget, String accountId, @JsonFormat(pattern = "yyyy-MM-dd") Date accountDateCreation, Level accountlevel, String userId,
		String userName, String userEmail, String userPassword, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate userDateBirth) {
	public ProjectResponse(Project project) {
		this(project.getId(), project.getName(), project.getDescription(), project.getDateStart(), project.getDateEnd(),
				project.getBudget(), project.getAccount().getId(), project.getAccount().getDateCreation(),
				project.getAccount().getLevel(), project.getAccount().getUser().getId(),
				project.getAccount().getUser().getName(), project.getAccount().getUser().getEmail(),
				project.getAccount().getUser().getPassword(), project.getAccount().getUser().getDateBirth());
	} 
}
