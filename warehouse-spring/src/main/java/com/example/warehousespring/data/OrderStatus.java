package com.example.warehousespring.data;

public enum OrderStatus {
    EXPECTING ("new"), COLLECTING ("collecting"), READY ("ready"), ISSUED("issued");
    final String text;
    OrderStatus(String text) {
        this.text = text;
    }
}
