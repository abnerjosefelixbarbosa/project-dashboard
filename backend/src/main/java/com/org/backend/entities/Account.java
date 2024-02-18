package com.org.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.org.backend.dtos.requests.CreateAccountRequest;
import com.org.backend.dtos.requests.LoginAccountRequest;
import com.org.backend.dtos.requests.UpdateAccountRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	private Date dateCreation = new Date();
	@Column(nullable = false)
	@Enumerated
	private Level level = Level.BASIC;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@OneToMany(mappedBy = "account")
	private Collection<Project> projects;

	public Account(CreateAccountRequest request) {
		User user = new User();
		user.setDateBirth(request.dateBirthUser());
		user.setEmail(request.emailUser());
		user.setPassword(request.passwordUser());
		user.setName(request.nameUser());
		this.user = user;
	}
	
	public Account(LoginAccountRequest request) {
		User user = new User();
		user.setEmail(request.emailUser());
		user.setPassword(request.passwordUser());
		this.user = user;
	}
	
	public Account(UpdateAccountRequest request) {
		this.level = request.level();
		User user = new User();
		user.setDateBirth(request.dateBirthUser());
		user.setEmail(request.emailUser());
		user.setPassword(request.passwordUser());
		user.setName(request.nameUser());
		this.user = user;
	}
	
	public void update(Account account) {
		this.level = account.getLevel();
		this.user.setDateBirth(account.getUser().getDateBirth());
		this.user.setEmail(account.getUser().getEmail());
		this.user.setName(account.getUser().getName());
		this.user.setPassword(account.getUser().getPassword());
	}
}
