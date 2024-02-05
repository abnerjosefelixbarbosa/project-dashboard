package com.org.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_account")
@Data
@NoArgsConstructor
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	private Date date_creation;
	@Column(nullable = false)
	@Enumerated
	private Level level = Level.BASIC;
	@ManyToOne
	@JoinColumn(
		name = "user_id",
		nullable = false
	)
	private User user;
	@OneToMany(mappedBy = "account")
	private Collection<Project> projects;
}
