package com.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
