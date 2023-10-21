package com.warehouse.project.data;

public enum Unit {

    PIECE("szt."), KILOGRAM("kg"), LITER("l");

    public String shortcut;

    Unit(String shortcut) {
        this.shortcut = shortcut;
    }
}
