package com.example.warehousespring.repository;

import com.example.warehousespring.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
