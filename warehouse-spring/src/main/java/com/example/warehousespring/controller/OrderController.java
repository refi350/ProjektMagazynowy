package com.example.warehousespring.controller;

import com.example.warehousespring.data.Order;
import com.example.warehousespring.repository.OrderRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/orders")
    public Order newOrder (@RequestBody Order newOrder) {
        return repository.save(newOrder);
    }
}
