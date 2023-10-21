package com.warehouse.project.data;

import jakarta.persistence.*;

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
    private StoreAction storeAction;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public ActionCommodity() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ActionCommodity(Commodity commodity, int quantity, StoreAction storeAction) {
        this.commodity = commodity;
        this.quantity = quantity;
        this.storeAction = storeAction;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public StoreAction getStoreAction() {
        return storeAction;
    }

    public void setStoreAction(StoreAction storeAction) {
        this.storeAction = storeAction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
