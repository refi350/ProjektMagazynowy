package com.example.warehousespring.controller;

import com.example.warehousespring.ContractorNotFoundException;
import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.data.StoreAction;
import com.example.warehousespring.service.StoreActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoreActionController {
    @Autowired
    private StoreActionService storeActionService;

    //------------------------------------ GET --------------------------------------------------------

    @GetMapping("/storeactions/{id}")
    public ResponseEntity<StoreAction> one(@PathVariable Long id) {
        StoreAction storeAction = storeActionService.getStoreActionById(id);
        if(storeAction == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(storeAction, HttpStatus.OK);
    }
    @GetMapping("/warehouses/{warehouseId}/storeactions/all")
    public ResponseEntity<List<StoreAction>> getAll(@PathVariable Long warehouseId) {
        List<StoreAction> storeActions = storeActionService.getAllStoreActions(warehouseId);
        if(storeActions == null || storeActions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(storeActions, HttpStatus.OK);
    }

    //------------------------------------ POST --------------------------------------------------------

    @PostMapping("/warehouses/{warehouseId}/storeactions")
    public ResponseEntity<StoreAction> newStoreAction(@PathVariable Long warehouseId, @RequestBody StoreAction newStoreAction) {
        StoreAction storeAction = storeActionService.addStoreActionToWarehouse(warehouseId, newStoreAction);
        if(storeAction != null) {
            return new ResponseEntity<>(storeAction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //------------------------------------ PUT --------------------------------------------------------

//    @PutMapping("/contractors/{id}")
//    public ResponseEntity<Contractor> updateContractor(@PathVariable Long id, @RequestBody Contractor contractor) {
//        try {
//            Contractor updatedContractor = contractorService.editContractor(id, contractor);
//            return ResponseEntity.ok(updatedContractor);
//        } catch (ContractorNotFoundException e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
    //------------------------------------ DELETE --------------------------------------------------------

    @DeleteMapping("/storeactions/{id}")
    public ResponseEntity<Void> deleteContractor(@PathVariable("id") Long id) {
        if (storeActionService.deleteStoreAction(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
