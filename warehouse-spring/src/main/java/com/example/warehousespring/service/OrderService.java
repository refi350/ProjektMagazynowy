package com.example.warehousespring.service;

import com.example.warehousespring.CommodityNotFoundException;
import com.example.warehousespring.data.Commodity;
import com.example.warehousespring.data.Order;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.OrderRepository;
import com.example.warehousespring.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Order addOrderToWarehouse(Long warehouseId, Order order) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            order.setWarehouse(warehouse);
            return orderRepository.save(order);
        }
        return null;
    }

    public List<Order> getAllOrders(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            return warehouse.getOrders();
        }
        return null;
    }

    public Order editOrderById(Long id, Order newOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new CommodityNotFoundException(id));
        order.editOrder(newOrder);
        orderRepository.save(order);
        return order;
    }

    public boolean deleteOrderById(Long orderId) {
        if(orderRepository.existsById(orderId)){
            orderRepository.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

}
