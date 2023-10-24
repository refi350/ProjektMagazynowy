package com.warehouse.project.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "contractor_id")
    private Address address;
    private String NIP;
    private boolean recipient;
    private boolean supplier;

    public List<StoreAction> getStore_action_list() {
        return store_action_list;
    }

    public void setStore_action_list(List<StoreAction> store_action_list) {
        this.store_action_list = store_action_list;
    }

    @OneToMany(mappedBy = "contractor")
    @JsonIgnore
    private List<StoreAction> store_action_list;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @ManyToOne
    @JsonIgnore
    private Warehouse warehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contractor() {
    }

    public Contractor(String name, Address address, String NIP, boolean recipient, boolean supplier) {
        this.name = name;
        this.address = address;
        this.NIP = NIP;
        this.recipient = recipient;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public boolean isRecipient() {
        return recipient;
    }

    public void setRecipient(boolean recipient) {
        this.recipient = recipient;
    }

    public boolean isSupplier() {
        return supplier;
    }

    public void setSupplier(boolean supplier) {
        this.supplier = supplier;
    }
}
