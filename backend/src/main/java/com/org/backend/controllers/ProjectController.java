package com.org.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.backend.dtos.requests.CreateProjectRequest;
import com.org.backend.dtos.requests.UpdateProjectRequest;
import com.org.backend.dtos.responses.ProjectResponse;
import com.org.backend.entities.Project;
import com.org.backend.exception.ValidationParamException;
import com.org.backend.interfaces.IProject;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	@Autowired
	private IProject iProject;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid CreateProjectRequest request) {
		Project response = iProject.createProject(new Project(request));
		return ResponseEntity.status(201).body(new ProjectResponse(response));
	}

	@PatchMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProjectResponse> updateProjectById(@RequestParam(required = false) String id,
			@RequestBody @Valid UpdateProjectRequest request) {
		if (id == null) {
			throw new ValidationParamException("id invalid");
		}
		Project response = iProject.updateProjectById(id, new Project(request));
		return ResponseEntity.status(200).body(new ProjectResponse(response));
	}
	
	@DeleteMapping("/delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProjectById(@RequestParam(required = false) String id) {
		if (id == null) {
			throw new ValidationParamException("id invalid");
		}
		iProject.deleteProjectById(id);
		return ResponseEntity.status(204).body(null);
	}
	
	@GetMapping("/list-all")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<ProjectResponse>> listAllProjectById(@RequestParam(required = false) String accountId, Pageable pageable) {
		if (accountId == null) {
			throw new ValidationParamException("account id invalid");
		}
		Page<Project> responses = iProject.findAllProjectByAccountId(accountId, pageable);
		return ResponseEntity.status(200).body(responses.map(ProjectResponse::new));
	}
}