package com.org.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.backend.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	Page<Project> findAllByAccountId(String accountId, Pageable pageable);
}