package com.warehouse.project.data;

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
    private int counter;

    // Counter for reservation - if order has READY OrderStatus
    // this counter is decremented
    @Column(name = "temp_counter")
    private int tempCounter;

    private long code;
    private String description;


    @Column(name = "expiration_date")
    private Date expirationDate;
    @Enumerated(EnumType.ORDINAL)
    private Unit unit;

    // todo
    @Lob
    @Column(name = "image", length = 1000)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore
    private Warehouse warehouse;

    public Commodity(String name) {
        this.name = name;
    }

    public StoreAction getStoreAction() {
        return storeAction;
    }

    public void setStoreAction(StoreAction storeAction) {
        this.storeAction = storeAction;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    private StoreAction storeAction;

    @ManyToOne
    private Order order;

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

    public int getTempCounter() {
        return tempCounter;
    }

    public void setTempCounter(int tempCounter) {
        this.tempCounter = tempCounter;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
