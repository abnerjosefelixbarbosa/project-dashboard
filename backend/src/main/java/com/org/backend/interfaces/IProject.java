package com.org.backend.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.org.backend.entities.Project;

public interface IProject {
	Project createProject(Project project);
	Project updateProjectById(String id, Project project);
	void deleteProjectById(String id);
	Page<Project> findAllProjectByAccountId(String accountId, Pageable pageable);
}
