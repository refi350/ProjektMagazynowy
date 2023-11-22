package com.example.warehousespring.controller;

import com.example.warehousespring.CommodityNotFoundException;
import com.example.warehousespring.data.Order;
import com.example.warehousespring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("warehouses/{warehouseId}/orders/all")
    public ResponseEntity<List<Order>> getAll(@PathVariable Long warehouseId) {
        List<Order> orders = orderService.getAllOrders(warehouseId);
        if(orders == null || orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("warehouses/{warehouseId}/orders")
    public ResponseEntity<Order> newOrder(@PathVariable Long warehouseId, @RequestBody Order newOrder) {
        Order order = orderService.addOrderToWarehouse(warehouseId, newOrder);
        if(order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            Order updatedOrder = orderService.editOrderById(id, order);
            return ResponseEntity.ok(updatedOrder);
        } catch (CommodityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if(orderService.deleteOrderById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
