package com.example.warehousespring.data;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Release extends StoreAction{

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Release() {
    }

    Release(LocalDateTime date, List<ActionCommodity> commodities, Contractor contractor, Long docNumber){
        super(date, commodities, contractor, docNumber);
    }

}
