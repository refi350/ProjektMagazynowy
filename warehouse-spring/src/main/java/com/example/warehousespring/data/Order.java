package com.example.warehousespring.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime submit_time = LocalDateTime.now();
    private LocalDateTime accept_time;
    private LocalDateTime completed_time;
    String order_status;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order() {
    }

    public Order(LocalDateTime submitTime) {
        this.submit_time = submitTime;
    }


    public LocalDateTime getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(LocalDateTime submit_time) {
        this.submit_time = submit_time;
    }

    public LocalDateTime getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(LocalDateTime accept_time) {
        this.accept_time = accept_time;
    }

    public LocalDateTime getCompleted_time() {
        return completed_time;
    }

    public void setCompleted_time(LocalDateTime completed_time) {
        this.completed_time = completed_time;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void editOrder(Order editedOrder) {
        if(!this.equals(editedOrder)) {
            this.setAccept_time(editedOrder.getAccept_time());
            this.setCompleted_time(editedOrder.getCompleted_time());
            this.setOrder_status(editedOrder.getOrder_status());
            this.setContractor(editedOrder.getContractor());
        }
    }
}
