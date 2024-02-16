package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dtos.requests.CreateProjectRequest;
import com.org.backend.dtos.responses.CreateProjectResponse;
import com.org.backend.entities.Project;
import com.org.backend.interfaces.IProject;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/projects")
public class ProjectController {
	@Autowired
	private IProject iProject;
	
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateProjectResponse> createProject(@RequestBody @Valid CreateProjectRequest request) {
		Project response = iProject.createProject(new Project(request));
		return ResponseEntity.status(201).body(new CreateProjectResponse(response));
	}
}