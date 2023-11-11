package com.example.warehousespring.repository;


import com.example.warehousespring.data.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Warehouse findByNameAndPassword(String username, String password);
}
