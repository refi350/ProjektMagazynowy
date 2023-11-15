package com.example.warehousespring.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public abstract class StoreAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "contractor_ID")
    private Contractor contractor;
    @OneToMany(mappedBy = "store_action")
    private List<ActionCommodity> commodities;

    public Long getDoc_number() {
        return doc_number;
    }

    public void setDoc_number(Long doc_number) {
        this.doc_number = doc_number;
    }

    private Long doc_number;

    @ManyToOne
    @JsonIgnore
    private Warehouse warehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected StoreAction() {
    }

    StoreAction(LocalDateTime date, List<ActionCommodity> commodities, Contractor contractor, Long docNumber){
        this.date = date;
        this.commodities = commodities;
        this.contractor = contractor;
        this.doc_number = docNumber;
    }

    public StoreAction(LocalDateTime date, Contractor contractor, List<ActionCommodity> commodities, Long docNumber) {
        this.date = date;
        this.contractor = contractor;
        this.commodities = commodities;
        this.doc_number = docNumber;
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
