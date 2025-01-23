package com.aviva.core.service.impl;

public class CurrencyData {

    private String name;
    private String price;

    public CurrencyData(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{\"name\": \"" + name + "\", \"price\": \"" + price + "\"}";
    }
}
