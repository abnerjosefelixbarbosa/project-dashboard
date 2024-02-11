package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.User;
import com.org.backend.interfaces.IUser;
import com.org.backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements IUser {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
}
