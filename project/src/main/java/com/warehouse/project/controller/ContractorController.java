package com.warehouse.project.controller;

import com.warehouse.project.data.Commodity;
import com.warehouse.project.data.Contractor;
import com.warehouse.project.repository.ContractorRepository;
import org.springframework.web.bind.annotation.*;

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
