package com.example.warehousespring.service;

import com.example.warehousespring.data.*;
import com.example.warehousespring.repository.ActionCommodityRepository;
import com.example.warehousespring.repository.OrderRepository;
import com.example.warehousespring.repository.StoreActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionCommodityService {
    @Autowired
    private StoreActionRepository storeActionRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ActionCommodityRepository actionCommodityRepository;

    public ActionCommodity getActionCommodityById(Long commodityId) {
        return actionCommodityRepository.findById(commodityId).orElse(null);
    }

    public List<ActionCommodity> getAllActionCommodities(Long storeActionId) {
        StoreAction storeAction = storeActionRepository.findById(storeActionId).orElse(null);
        if (storeAction != null) {
            return storeAction.getCommodities();
        }
        return null;
    }

    public ActionCommodity addActionCommodityToOrder(Long orderId, ActionCommodity actionCommodity) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            actionCommodity.setOrder(order);
            return actionCommodityRepository.save(actionCommodity);
        }
        return null;
    }

    public ActionCommodity addActionCommodityToStoreAction(Long storeActionId, ActionCommodity actionCommodity) {
        StoreAction storeAction = storeActionRepository.findById(storeActionId).orElse(null);
        if (storeAction != null) {
            actionCommodity.setStore_action(storeAction);
            return actionCommodityRepository.save(actionCommodity);
        }
        return null;
    }

    public boolean deleteActionCommodityById(Long id) {
        if(actionCommodityRepository.existsById(id)) {
            actionCommodityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
