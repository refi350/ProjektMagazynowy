package com.warehouse.project.controller;

import com.warehouse.project.WarehouseNotFoundException;
import com.warehouse.project.data.Commodity;
import com.warehouse.project.repository.CommodityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommodityController {

    private final CommodityRepository repository;

    CommodityController(CommodityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/commodities/{id}")
    Commodity one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
    }
}
