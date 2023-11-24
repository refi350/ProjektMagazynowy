package com.example.warehousespring.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

//Position on the store action list or order
@Entity
public class ActionCommodity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "store_action_id")
    @JsonIgnore
    private StoreAction store_action;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;


    public ActionCommodity() {
    }


    public ActionCommodity(Commodity commodity, int quantity) {
        this.commodity = commodity;
        this.quantity = quantity;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public StoreAction getStore_action() {
        return store_action;
    }

    public void setStore_action(StoreAction storeAction) {
        this.store_action = storeAction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
