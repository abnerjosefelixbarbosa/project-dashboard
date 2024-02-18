package com.org.backend.interfaces;

import com.org.backend.entities.Project;

public interface IProject {
	Project createProject(Project project);
	Project updateProjectById(String id, Project project);
	void deleteProjectById(String id);
}
