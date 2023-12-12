package com.example.warehousespring.data.store_action;

import jakarta.persistence.Entity;

@Entity
public class Receipt extends StoreAction {
    Receipt(){
    }

    @Override
    public void setDocNumber() {
        this.doc_number = this.warehouse.countReceiptNumber();
    }
}
