package com.warehouse.project.data;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

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

    // todo
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "warehouse")
    List<Contractor> contractors;
    @OneToMany(mappedBy = "warehouse")
    private List<Commodity> commodities;

    @Column(name = "store_actions")
    @OneToMany(mappedBy = "warehouse")
    private List<StoreAction> store_actions;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    Warehouse() {}

    Warehouse(String name, String password, Address address, byte[] image, String color, Owner owner, List<Commodity> commodityList) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.image = image;
        this.color = color;
        this.owner = owner;
        this.commodities = commodityList;
        System.out.println(this.commodities.get(0).getName());
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
        return Objects.equals(this.id, warehouse.id) && Objects.equals(this.name, warehouse.name)
                && Objects.equals(this.address, warehouse.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address);
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

}