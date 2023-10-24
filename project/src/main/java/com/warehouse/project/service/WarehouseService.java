package com.warehouse.project.service;

import com.warehouse.project.WarehouseNotFoundException;
import com.warehouse.project.data.Warehouse;
import com.warehouse.project.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<String> getAllWarehousesNames() {
        return warehouseRepository.findAll().stream().map(Warehouse::getName).toList();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse getWarehouseByNameAndPassword(String name, String password) {
        return warehouseRepository.findByNameAndPassword(name, password);
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public Warehouse editWarehouse(Long id, Warehouse newWarehouse) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
        warehouse.editWarehouse(newWarehouse);
        return warehouse;
    }
}
