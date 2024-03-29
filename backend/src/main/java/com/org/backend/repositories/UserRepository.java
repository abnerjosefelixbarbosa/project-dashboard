package com.org.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
}
