package com.example.warehousespring.controller;

import com.example.warehousespring.ContractorNotFoundException;
import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractorController {

    @Autowired
    private ContractorService contractorService;


    //------------------------------------ GET --------------------------------------------------------


    @GetMapping("warehouses/{warehouseId}/contractors/all")
    public ResponseEntity<List<Contractor>> getAll(@PathVariable Long warehouseId) {
        List<Contractor> contractors = contractorService.getAllContractors(warehouseId);
        if(contractors == null || contractors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contractors, HttpStatus.OK);
    }

    @GetMapping("/contractors/{id}")
    public ResponseEntity<Contractor> one(@PathVariable Long id) {
        Contractor contractor = contractorService.getContractorById(id);
        if(contractor != null) {
            return new ResponseEntity<>(contractor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    //------------------------------------ POST --------------------------------------------------------
    @PostMapping("warehouses/{warehouseId}/contractors")
    public ResponseEntity<Contractor> newCommodity(@PathVariable Long warehouseId, @RequestBody Contractor newContractor) {
        Contractor contractor = contractorService.addContractorToWarehouse(warehouseId, newContractor);
        if(contractor != null) {
            return new ResponseEntity<>(contractor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //------------------------------------ PUT --------------------------------------------------------

    @PutMapping("/contractors/{id}")
    public ResponseEntity<Contractor> updateContractor(@PathVariable Long id, @RequestBody Contractor contractor) {
        try {
            Contractor updatedContractor = contractorService.editContractor(id, contractor);
            return ResponseEntity.ok(updatedContractor);
        } catch (ContractorNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    //------------------------------------ DELETE --------------------------------------------------------

    @DeleteMapping("/contractors/{id}")
    public ResponseEntity<Void> deleteContractor(@PathVariable("id") Long id) {
        if (contractorService.deleteContractor(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
