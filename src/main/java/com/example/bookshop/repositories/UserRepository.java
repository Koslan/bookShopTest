package com.example.bookshop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByLogin(String login);

	Optional<User> findById(String name);
}
