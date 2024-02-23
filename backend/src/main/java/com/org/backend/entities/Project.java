package com.org.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.org.backend.dtos.requests.CreateProjectRequest;
import com.org.backend.dtos.requests.UpdateProjectRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStart;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateEnd;
	@Column(nullable = false)
	private BigDecimal budget;
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
	
	public Project(CreateProjectRequest request) {
		Account account = new Account();
		account.setId(request.accountId());
		this.name = request.name();
		this.description = request.description();
		this.dateStart = request.dateStart();
		this.dateEnd = request.dateEnd();
		this.budget = request.budget();
		this.account = account;
	}
	
	public Project(UpdateProjectRequest request) {
		Account account = new Account();
		this.name = request.name();
		this.description = request.description();
		this.dateStart = request.dateStart();
		this.dateEnd = request.dateEnd();
		this.budget = request.budget();
		this.account = account;
	}
	
	public void updateProjectByid(Project project) {
		this.name = project.getName();
		this.description = project.getDescription();
		this.dateStart = project.getDateStart();
		this.dateEnd = project.getDateEnd();
		this.budget = project.getBudget();
	}
}