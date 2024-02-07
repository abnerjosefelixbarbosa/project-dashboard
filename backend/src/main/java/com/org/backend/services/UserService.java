package com.org.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend.entities.User;
import com.org.backend.exception.BusinessException;
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
	
	public void validateSave(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new BusinessException("email exist");
		}
		if (userRepository.existsByPassword(user.getPassword())) {
			throw new BusinessException("password exist");
		}
	}
}
