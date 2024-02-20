package com.org.backend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.org.backend.entities.Account;
import com.org.backend.entities.Project;
import com.org.backend.exception.BusinessException;
import com.org.backend.exception.NotFoundException;
import com.org.backend.interfaces.IAccount;
import com.org.backend.interfaces.IProject;
import com.org.backend.repositories.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService implements IProject {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private IAccount iAccount;
	
	@Transactional
	public Project createProject(Project project) {
		Account accountFound = iAccount.findAccountById(project.getAccount().getId());
		project.setAccount(accountFound);
		validateProject(project);
		return projectRepository.save(project);
	}
	
	@Transactional
	public Project updateProjectById(String id, Project project) {
		validateProject(project);
		Project projectFound = findProjectById(id);
		projectFound.updateProjectByid(project);
		return projectRepository.save(projectFound);
	}
	
	public void deleteProjectById(String id) {
	    findProjectById(id);
		projectRepository.deleteById(id);
	}
	
	public Project findProjectById(String id) {
		return projectRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("project id not found");
		});
	}
	
	public Page<Project> findAllProjectByAccountId(String accountId, Pageable pageable) {
		iAccount.findAccountById(accountId);
		return projectRepository.findAllByAccountId(accountId, pageable);
	}
	
	private void validateProject(Project project) {
		Date date = new Date();
		if (project.getDateStart().toString().equals(date.toString())) {
			throw new BusinessException("date start invalid");
		}
		if (project.getDateEnd().before(project.getDateStart())) {
			throw new BusinessException("date end invalid");
		}
        if (project.getDateEnd().equals(project.getDateStart())) {
        	throw new BusinessException("date end invalid");
		}
        if (project.getBudget().doubleValue() == 0.00) {
        	throw new BusinessException("budget invalid");
        }
        if (project.getBudget().scale() != 2) {
        	throw new BusinessException("budget invalid");
        }
	}
}
