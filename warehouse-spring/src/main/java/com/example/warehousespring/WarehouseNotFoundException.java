package com.example.warehousespring;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException(Long id) {
        super("Could not find warehouse " + id);
    }
}
