package com.example.warehousespring.data.store_action;

import com.example.warehousespring.data.ActionCommodity;
import com.example.warehousespring.data.Contractor;
import com.example.warehousespring.data.Warehouse;
import com.example.warehousespring.data.store_action.Receipt;
import com.example.warehousespring.data.store_action.Release;
import com.example.warehousespring.data.store_action.ResolveNumbers;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Receipt.class, name = "Receipt"),
        @JsonSubTypes.Type(value = Release.class, name = "Release")
})
public abstract class StoreAction implements ResolveNumbers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "contractor_ID")
    private Contractor contractor;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "store_action")
    @JsonManagedReference
    private List<ActionCommodity> commodities = Collections.emptyList();

    protected Long doc_number;

    public Long getDoc_number() {
        return doc_number;
    }

    public void setDoc_number(Long doc_number) {
        this.doc_number = doc_number;
    }

    @ManyToOne
    @JsonIgnore
    protected Warehouse warehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreAction() {
    }

    StoreAction(LocalDateTime date, List<ActionCommodity> commodities, Contractor contractor, Long doc_number){
        this.date = date;
        this.commodities = commodities;
        this.contractor = contractor;
        this.doc_number = doc_number;
    }

    public StoreAction(LocalDateTime date, Contractor contractor, List<ActionCommodity> commodities, Long doc_number) {
        this.date = date;
        this.contractor = contractor;
        this.commodities = commodities;
        this.doc_number = doc_number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public List<ActionCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<ActionCommodity> commodities) {
        this.commodities = commodities;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
