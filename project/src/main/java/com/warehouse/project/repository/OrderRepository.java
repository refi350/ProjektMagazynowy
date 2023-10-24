package com.warehouse.project.repository;

import com.warehouse.project.data.Commodity;
import com.warehouse.project.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
