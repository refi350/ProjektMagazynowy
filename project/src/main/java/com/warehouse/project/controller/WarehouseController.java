package com.warehouse.project.controller;

import com.warehouse.project.WarehouseNotFoundException;
import com.warehouse.project.data.Warehouse;
import com.warehouse.project.repository.WarehouseRepository;
import org.springframework.web.bind.annotation.*;

@RestController
class WarehouseController {

    private final WarehouseRepository repository;

    WarehouseController(WarehouseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/warehouses")
    Warehouse newWarehouse(@RequestBody Warehouse newWarehouse) {
        return repository.save(newWarehouse);
    }

    @GetMapping("/warehouses/{id}")
    Warehouse one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
    }

    //    @GetMapping("/warehouses/{name}")
//    Warehouse getWarehouse(@RequestParam String name) {
//        return repository.findByWarehouseName(name);
//    }

//    @GetMapping("/warehouses/{name}")
//    Warehouse getWarehouse(@RequestParam String name, @RequestParam String password) {
//        return repository.findByWarehouseNameAndPassword(name, password);
//    }
}
