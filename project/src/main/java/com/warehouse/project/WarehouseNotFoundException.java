package com.warehouse.project;

import jdk.jshell.spi.ExecutionControl;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException(Long id) {
        super("Could not find warehouse " + id);
    }
}
