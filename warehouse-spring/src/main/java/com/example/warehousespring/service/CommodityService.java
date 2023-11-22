package com.example.warehousespring.service;

import com.example.warehousespring.CommodityNotFoundException;
import com.example.warehousespring.data.Commodity;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.repository.CommodityRepository;
import com.example.warehousespring.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Commodity addCommodityToWarehouse(Long warehouseId, Commodity commodity) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            commodity.setWarehouse(warehouse);
            return commodityRepository.save(commodity);
        }
        return null;
    }

    public List<Commodity> getAllCommodities(Long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            return warehouse.getCommodities();
        }
        return null;
    }

    public Commodity getCommodityById(Long commodityId) {
        return commodityRepository.findById(commodityId).orElse(null);
    }

    public Commodity editCommodityById(Long id, Commodity newCommodity) {
        Commodity commodity = commodityRepository.findById(id)
                .orElseThrow(() -> new CommodityNotFoundException(id));
        commodity.editCommodity(newCommodity);
        commodityRepository.save(commodity);
        return commodity;
    }

    public boolean deleteCommodityById(Long commodityId) {
        if(commodityRepository.existsById(commodityId)){
            commodityRepository.deleteById(commodityId);
            return true;
        } else {
            return false;
        }
    }
}
