package com.example.warehousespring.data;

public enum Unit {

    PIECE("szt."), KILOGRAM("kg"), LITER("l");

    public String shortcut;

    Unit(String shortcut) {
        this.shortcut = shortcut;
    }
}
