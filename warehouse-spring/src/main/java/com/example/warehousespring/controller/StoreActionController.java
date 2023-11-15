package com.example.warehousespring.controller;

import com.example.warehousespring.data.StoreAction;
import com.example.warehousespring.repository.StoreActionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreActionController {
    private final StoreActionRepository repository;


    public StoreActionController(StoreActionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/storeactions")
    public StoreAction newStoreAction (@RequestBody StoreAction newStoreAction) {
        return repository.save(newStoreAction);
    }
}
