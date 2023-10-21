package com.warehouse.project.repository;


import com.warehouse.project.data.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    //Warehouse findByWarehouseName(String userName);

    //Warehouse findByWarehouseNameAndPassword(String username, String password);
}
