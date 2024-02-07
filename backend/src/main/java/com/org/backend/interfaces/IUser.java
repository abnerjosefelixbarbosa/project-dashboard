package com.org.backend.interfaces;

import com.org.backend.entities.User;

public interface IUser {
	User save(User user);
	void validateSave(User user);
}
