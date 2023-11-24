package com.example.warehousespring.controller;

import com.example.warehousespring.CommodityNotFoundException;
import com.example.warehousespring.data.ActionCommodity;
import com.example.warehousespring.data.Commodity;
import com.example.warehousespring.data.StoreAction;
import com.example.warehousespring.service.ActionCommodityService;
import com.example.warehousespring.service.StoreActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActionCommodityController {

    @Autowired
    private ActionCommodityService actionCommodityService;


    @GetMapping("actioncommodities/{actionCommoditiesId}")
    public ResponseEntity<ActionCommodity> one(@PathVariable Long actionCommoditiesId) {
        ActionCommodity actionCommodity = actionCommodityService.getActionCommodityById(actionCommoditiesId);
        if(actionCommodity != null) {
            return new ResponseEntity<>(actionCommodity, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("storeactions/{storeActionId}/actioncommodities/all")
    public ResponseEntity<List<ActionCommodity>> getAll(@PathVariable Long storeActionId) {
        List<ActionCommodity> actionCommodities = actionCommodityService.getAllActionCommodities(storeActionId);
        if(actionCommodities == null || actionCommodities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actionCommodities, HttpStatus.OK);
    }

    @PostMapping("storeactions/{storeActionId}/actioncommodities")
    public ResponseEntity<ActionCommodity> newActionCommodity(@PathVariable Long storeActionId, @RequestBody ActionCommodity newActionCommodity) {
        ActionCommodity actionCommodity = actionCommodityService.addActionCommodityToStoreAction(storeActionId, newActionCommodity);
        if(actionCommodity != null) {
            return new ResponseEntity<>(actionCommodity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/commodities/{id}")
//    public ResponseEntity<Commodity> updateCommodity(@PathVariable Long id, @RequestBody Commodity commodity) {
//        try {
//            Commodity updatedCommodity = commodityService.editCommodityById(id, commodity);
//            return ResponseEntity.ok(updatedCommodity);
//        } catch (CommodityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
    @DeleteMapping("actioncommodities/{id}")
    public ResponseEntity<Void> deleteActionCommodity(@PathVariable Long id) {
        if(actionCommodityService.deleteActionCommodityById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
