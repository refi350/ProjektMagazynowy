package com.example.warehousespring.controller;

import com.example.warehousespring.CommodityNotFoundException;
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

    @GetMapping("commodities/{commodityId}")
    public ResponseEntity<Commodity> one(@PathVariable Long commodityId) {
        Commodity commodity = commodityService.getCommodityById(commodityId);
        if(commodity != null)
            return new ResponseEntity<>(commodity, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("warehouses/{warehouseId}/commodities/all")
    public ResponseEntity<List<Commodity>> getAll(@PathVariable Long warehouseId) {
        List<Commodity> commodities = commodityService.getAllCommodities(warehouseId);
        if(commodities == null || commodities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commodities, HttpStatus.OK);
    }

    @PostMapping("warehouses/{warehouseId}/commodities")
    public ResponseEntity<Commodity> newCommodity(@PathVariable Long warehouseId, @RequestBody Commodity newCommodity) {
        Commodity commodity = commodityService.addCommodityToWarehouse(warehouseId, newCommodity);
        if(commodity != null) {
            return new ResponseEntity<>(commodity, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/commodities/{id}")
    public ResponseEntity<Commodity> updateCommodity(@PathVariable Long id, @RequestBody Commodity commodity) {
        try {
            Commodity updatedCommodity = commodityService.editCommodityById(id, commodity);
            return ResponseEntity.ok(updatedCommodity);
        } catch (CommodityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/commodities/{id}")
    public ResponseEntity<Void> deleteCommodity(@PathVariable Long id) {
        if(commodityService.deleteCommodityById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
