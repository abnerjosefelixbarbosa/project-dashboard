package com.org.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.backend.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

}
