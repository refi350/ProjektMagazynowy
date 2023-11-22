package com.example.warehousespring.service;

import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.data.StoreAction;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.ContractorRepository;
import com.example.warehousespring.repository.StoreActionRepository;
import com.example.warehousespring.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreActionService {
    @Autowired
    private StoreActionRepository storeActionRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<StoreAction> getAllStoreActions(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            return warehouse.getStore_actions();
        }
        return null;
    }

    public List<StoreAction> getAllStoreActionsByContractor(Long contractorId) {
        Contractor contractor = contractorRepository.findById(contractorId).orElse(null);
        if (contractor != null) {
            return contractor.getStore_action_list();
        }
        return null;
    }

    public StoreAction addStoreActionToWarehouse(Long warehouseId, StoreAction storeAction) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            storeAction.setWarehouse(warehouse);
            return storeActionRepository.save(storeAction);
        }
        return null;
    }

}
