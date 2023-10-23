package com.warehouse.project.controller;

import com.warehouse.project.WarehouseNotFoundException;
import com.warehouse.project.data.Commodity;
import com.warehouse.project.data.Warehouse;
import com.warehouse.project.repository.CommodityRepository;
import com.warehouse.project.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("warehouse/{warehouseId}/commodities/{commodityId}")
    Commodity one(@PathVariable Long warehouseId, @PathVariable int commodityId) {
        return commodityService.getCommodityById(warehouseId, commodityId);
    }

    @GetMapping("warehouse/{warehouseId}/commodities/all")
    public List<Commodity> getAll(@PathVariable Long warehouseId) {
        return commodityService.getAllCommodities(warehouseId);
    }

    @PostMapping("warehouses/{warehouseId}/commodities")
    public Commodity newCommodity(@PathVariable Long warehouseId, @RequestBody Commodity newCommodity) {
        return commodityService.addCommodityToWarehouse(warehouseId, newCommodity);
    }

//    @PutMapping("/commodities/{id}")
//    public Commodity updateCommodity(@PathVariable Long id, @RequestBody Commodity commodity) {
//        commodity.setId(id);
//        return repository.save(commodity);
//    }
//
//    @DeleteMapping("/commodities/{id}")
//    public void deleteCommodity(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
}
