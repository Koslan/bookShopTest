package com.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
