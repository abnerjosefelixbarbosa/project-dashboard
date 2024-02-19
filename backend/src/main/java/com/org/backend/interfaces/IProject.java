package com.org.backend.interfaces;

import java.util.List;

import com.org.backend.entities.Project;

public interface IProject {
	Project createProject(Project project);
	Project updateProjectById(String id, Project project);
	void deleteProjectById(String id);
	List<Project> findAllProjectByAccountId(String accountId);
}
