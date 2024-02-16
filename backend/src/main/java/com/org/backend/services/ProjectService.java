package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.Account;
import com.org.backend.entities.Project;
import com.org.backend.exception.BusinessException;
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
		Account account = iAccount.findAccountById(project.getAccount().getId());
		project.setAccount(account);
		validateCreateProject(project);
		return projectRepository.save(project);
	}
	
	private void validateCreateProject(Project project) {
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
