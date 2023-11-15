package com.example.warehousespring.data;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Receipt extends StoreAction{

    Receipt(){

    }
    Receipt(LocalDateTime date, List<ActionCommodity> commodities, Contractor contractor, Long docNumber){
        super(date, commodities, contractor, docNumber);
    }
}
