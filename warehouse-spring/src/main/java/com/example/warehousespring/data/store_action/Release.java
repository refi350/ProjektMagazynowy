package com.example.warehousespring.data.store_action;

import com.example.warehousespring.data.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Release extends StoreAction implements ResolveNumbers {

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

    @Override
    public void setDocNumber() {
        this.doc_number = this.warehouse.countReleaseNumber();
    }
}
