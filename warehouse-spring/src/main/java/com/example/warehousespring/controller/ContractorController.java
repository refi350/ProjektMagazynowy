package com.example.warehousespring.controller;

import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.repository.ContractorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractorController {

    private final ContractorRepository repository;

    public ContractorController(ContractorRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/contractors")
    public Contractor newContractor(@RequestBody Contractor newContractor) {
        return repository.save(newContractor);
    }

}
