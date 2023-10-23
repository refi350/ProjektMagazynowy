package com.warehouse.project.service;

import com.warehouse.project.data.Commodity;
import com.warehouse.project.data.Warehouse;
import com.warehouse.project.repository.CommodityRepository;
import com.warehouse.project.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Commodity getCommodityById(Long warehouseId, int commodityId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);
        if (warehouse != null) {
            return warehouse.getCommodities().get(commodityId);
        }
        return null;
    }
}