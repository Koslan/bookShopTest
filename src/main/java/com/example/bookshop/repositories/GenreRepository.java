package com.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
