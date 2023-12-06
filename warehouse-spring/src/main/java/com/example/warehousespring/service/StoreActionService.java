package com.example.warehousespring.service;

import com.example.warehousespring.data.ActionCommodity;
import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.data.StoreAction;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.ActionCommodityRepository;
import com.example.warehousespring.repository.ContractorRepository;
import com.example.warehousespring.repository.StoreActionRepository;
import com.example.warehousespring.repository.WarehouseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public StoreAction addStoreActionToWarehouse(Long warehouseId, StoreAction storeAction) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            storeAction.setWarehouse(warehouse);
            if (storeAction.getCommodities() != null) {
                for (ActionCommodity actionCommodity : storeAction.getCommodities()) {
                    // Upewnij się, że towar jest zarządzany przez Hibernate
                    if (actionCommodity.getId() == null) {
                        entityManager.persist(actionCommodity);
                    } else {
                        actionCommodity = entityManager.merge(actionCommodity);
                    }

                    // Ustaw relację
                    actionCommodity.setStore_action(storeAction);
                }
            }
            // Save the storeAction after handling the commodities
            entityManager.persist(storeAction);
            // Make sure the storeAction is in the persistence context
            entityManager.flush();
            entityManager.refresh(storeAction);
        }
        return storeAction;
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
