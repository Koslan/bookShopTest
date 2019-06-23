package com.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookshop.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
