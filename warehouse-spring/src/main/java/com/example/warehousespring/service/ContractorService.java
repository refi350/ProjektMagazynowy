package com.example.warehousespring.service;

import com.example.warehousespring.ContractorNotFoundException;
import com.example.warehousespring.data.Commodity;
import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.ContractorRepository;
import com.example.warehousespring.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Contractor> getAllContractors(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            return warehouse.getContractors();
        }
        return null;
    }

    public List<String> getAll() {
        return contractorRepository.findAll().stream().map(Contractor::getName).toList();
    }

    public Contractor getContractorById(Long id) {
        return contractorRepository.findById(id).orElse(null);
    }

    public Contractor saveContractor(Contractor Contractor) {
        return contractorRepository.save(Contractor);
    }

    public Contractor addContractorToWarehouse(Long warehouseId, Contractor contractor) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            contractor.setWarehouse(warehouse);
            return contractorRepository.save(contractor);
        }
        return null;
    }

    public boolean deleteContractor(Long id) {
        if(contractorRepository.existsById(id)) {
            contractorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Contractor editContractor(Long id, Contractor newContractor) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new ContractorNotFoundException(id));
        contractor.editContractor(newContractor);
        contractorRepository.save(contractor);
        return contractor;
    }
}
