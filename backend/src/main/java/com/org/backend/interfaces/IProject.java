package com.org.backend.interfaces;

import com.org.backend.entities.Project;

public interface IProject {
	Project createProject(Project project);
	Project updateProjectByid(String id, Project project);
}
