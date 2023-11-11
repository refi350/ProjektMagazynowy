package com.example.warehousespring.controller;

import com.example.warehousespring.data.Commodity;
import com.example.warehousespring.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("warehouse/{warehouseId}/commodities/{commodityId}")
    public ResponseEntity<Commodity> one(@PathVariable Long warehouseId, @PathVariable int commodityId) {
        Commodity commodity = commodityService.getCommodityById(warehouseId, commodityId);
        if(commodity != null)
            return new ResponseEntity<>(commodity, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("warehouses/{warehouseId}/commodities/all")
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
