package com.example.warehousespring.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "commodities")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    // Counter for presence of commodity in stock
    // if commodity is released this counter is decremented
    // if commodity is receipted this counter is increased
    private int counter = 0;

    // Counter for reservation - if order has READY OrderStatus
    // this counter is decremented
    @Column(name = "temp_counter")
    private int temp_counter = 0;

    private long code;
    private String description;

    private String unit;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;


    public Commodity(String name) {
        this.name = name;
    }


    public Commodity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getTemp_counter() {
        return temp_counter;
    }

    public void setTemp_counter(int tempCounter) {
        this.temp_counter = tempCounter;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void editCommodity(Commodity editedCommodity) {
        if(!this.equals(editedCommodity)) {
            this.setName(editedCommodity.getName());
            this.setCounter(editedCommodity.getCounter());
            this.setTemp_counter(editedCommodity.getTemp_counter());
            this.setCode(editedCommodity.getCode());
            this.setDescription(editedCommodity.getDescription());
            this.setUnit(editedCommodity.getUnit());
        }
    }

}
