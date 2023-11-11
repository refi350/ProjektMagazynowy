package com.example.warehousespring.controller;

import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    // ------------------ GET -----------------------------------------
    @GetMapping("/warehouses/all")
    public List<Warehouse> getAll() {
        return warehouseService.getAllWarehouses();
    }
    @GetMapping("/warehouses/names")
    public List<String> getAllNames() {
        return warehouseService.getAllWarehousesNames();
    }

    @GetMapping("/warehouses/{id}")
    public Warehouse one(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @GetMapping("/warehouses/login")
    public ResponseEntity<Warehouse> loginWarehouse(@RequestParam String name, @RequestParam String password) {
        Warehouse warehouse = warehouseService.getWarehouseByNameAndPassword(name, password);
        if(warehouse != null)
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ------------------ POST -----------------------------------------

    @PostMapping("/warehouses")
    public Warehouse newWarehouse(@RequestBody Warehouse newWarehouse) {
        return warehouseService.saveWarehouse(newWarehouse);
    }



    // ------------------ DELETE -----------------------------------------
    @DeleteMapping("/warehouses/{id}")
    public void deleteWarehouse(@PathVariable("id") Long id) {
        warehouseService.deleteWarehouse(id);
    }

     // ------------------ PUT -----------------------------------------
    @PutMapping("/warehouses/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        Warehouse updatedWarehouse = warehouseService.editWarehouse(id, warehouse);
        return ResponseEntity.ok(updatedWarehouse);
    }
}
