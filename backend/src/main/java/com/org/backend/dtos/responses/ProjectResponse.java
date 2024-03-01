package com.org.backend.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.org.backend.entities.Project;

public record ProjectResponse(
	String id,
	String name,
	String description,
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate dateStart,
	@JsonFormat(pattern = "yyyy-MM-dd") 
	LocalDate dateEnd,
	BigDecimal budget,
	AccountResponse account
) {
	public ProjectResponse(Project project) {
		this(
			project.getId(),
			project.getName(),
			project.getDescription(),
			project.getDateStart(),
			project.getDateEnd(),
			project.getBudget(),
			new AccountResponse(project.getAccount())
		);
	} 
}