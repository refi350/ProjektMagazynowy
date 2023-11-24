package com.example.warehousespring.service;

import com.example.warehousespring.data.ActionCommodity;
import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.data.StoreAction;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.ActionCommodityRepository;
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
    private ActionCommodityRepository actionCommodityRepository;

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public StoreAction getStoreActionById(Long id) {
        return storeActionRepository.findById(id).orElse(null);
    }

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
        //Contractor contractor = contractorRepository.findById(storeAction.getContractor().getId()).orElse(null);
        if (warehouse != null) {
            storeAction.setWarehouse(warehouse);
            List<ActionCommodity> commodities = storeAction.getCommodities();
            actionCommodityRepository.saveAll(commodities);
            return storeActionRepository.save(storeAction);
        }
        return null;
    }

    public boolean deleteStoreAction(Long id) {
        if(storeActionRepository.existsById(id)) {
            storeActionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
