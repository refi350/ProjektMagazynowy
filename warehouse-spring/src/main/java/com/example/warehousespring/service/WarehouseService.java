package com.example.warehousespring.service;

import com.example.warehousespring.WarehouseNotFoundException;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.WarehouseRepository;
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

    public boolean deleteWarehouse(Long id) {
        if(warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Warehouse editWarehouse(Long id, Warehouse newWarehouse) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
        warehouse.editWarehouse(newWarehouse);
        return warehouse;
    }
}
