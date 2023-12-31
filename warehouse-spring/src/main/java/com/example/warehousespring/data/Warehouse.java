package com.example.warehousespring.data;

import com.example.warehousespring.data.store_action.StoreAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Warehouse {

    private @Id @GeneratedValue Long id;
    private String name;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    private String color;

    public List<StoreAction> getStore_actions() {
        return store_actions;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "warehouse")
    List<Contractor> contractors = Collections.emptyList();
    @OneToMany(mappedBy = "warehouse")
    private List<Commodity> commodities = Collections.emptyList();

    @Column(name = "store_actions")
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<StoreAction> store_actions = Collections.emptyList();

    @JsonIgnore
    private Long releaseCounter = 1L;

    @JsonIgnore
    private Long receiptCounter = 1L;

    @Column(name = "store_actions")
    @OneToMany(mappedBy = "warehouse")
    private List<Order> orders = Collections.emptyList();

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    Warehouse() {}

    Warehouse(String name, String password, Address address, String color, Owner owner, List<Commodity> commodityList) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.color = color;
        this.owner = owner;
        this.commodities = commodityList;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Warehouse))
            return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(this.id, warehouse.id) &&
                Objects.equals(this.name, warehouse.name) &&
                Objects.equals(this.address, warehouse.address) &&
                Objects.equals(this.color, warehouse.color) &&
                Objects.equals(this.owner, warehouse.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address, this.color, this.owner);
    }

    @Override
    public String toString() {
        return "Warehouse{" + "id=" + this.id + ", name='" + this.name + '\'' + ", address='" + this.address + '\'' + '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }


    public void setStore_actions(List<StoreAction> store_actions) {
        this.store_actions = store_actions;
    }

    public void addAction(StoreAction action) {
        this.store_actions.add(action);
    }

    public void editWarehouse(Warehouse editedWarehouse) {
        if(!this.equals(editedWarehouse)) {
            this.setName(editedWarehouse.getName());
            this.setPassword(editedWarehouse.getPassword());
            this.setAddress(editedWarehouse.getAddress());
            this.setColor(editedWarehouse.getColor());
            this.setOwner(editedWarehouse.getOwner());
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getReleaseCounter() {
        return releaseCounter;
    }

    public void setReleaseCounter(Long releaseCounter) {
        this.releaseCounter = releaseCounter;
    }

    public Long getReceiptCounter() {
        return receiptCounter;
    }

    public Long countReceiptNumber() {
        Long number = getReceiptCounter();
        receiptCounter++;
        return number;
    }

    public Long countReleaseNumber() {
        Long number = getReleaseCounter();
        releaseCounter++;
        return number;
    }
    public void setReceiptCounter(Long receiptCounter) {
        this.receiptCounter = receiptCounter;
    }
}