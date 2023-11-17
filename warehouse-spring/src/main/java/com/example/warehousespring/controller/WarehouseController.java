package com.example.warehousespring.controller;

import com.example.warehousespring.WarehouseNotFoundException;
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
    public ResponseEntity<List<Warehouse>> getAll() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        if (warehouses != null) {
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/warehouses/names")
    public ResponseEntity<List<String>> getAllNames() {
        List<String> warehousesNames = warehouseService.getAllWarehousesNames();
        if(warehousesNames != null) {
            return new ResponseEntity<>(warehousesNames, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/warehouses/{id}")
    public ResponseEntity<Warehouse> one(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if(warehouse != null) {
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<Void> deleteWarehouse(@PathVariable("id") Long id) {
        if (warehouseService.deleteWarehouse(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

     // ------------------ PUT -----------------------------------------
    @PutMapping("/warehouses/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        try {
            Warehouse updatedWarehouse = warehouseService.editWarehouse(id, warehouse);
            return ResponseEntity.ok(updatedWarehouse);
        } catch (WarehouseNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
