package org.example.javastringboots.repository;

import org.example.javastringboots.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReposetory extends JpaRepository<Order, Long> {
}
