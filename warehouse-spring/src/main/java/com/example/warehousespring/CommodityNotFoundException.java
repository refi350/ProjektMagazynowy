package com.example.warehousespring;

public class CommodityNotFoundException extends RuntimeException{
    public CommodityNotFoundException(Long id) {
        super("Could not find commodity " + id);
    }
}
