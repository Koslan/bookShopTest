package com.example.bookshop.services;

import java.util.Optional;

import com.example.bookshop.models.User;

public interface UserService {
	void save(User user);

	Optional<User> findByLogin(String login);
}
