package com.example.shopping_cart;

public class Product_details {
    private String name;
    private double price;
    private int quantity;

    public Product_details(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String get_Name() {
        return this.name;
    }
    public double get_Price() {
        return this.price;
    }
    public int get_Quantity() {
        return this.quantity;
    }
    public void set_Quantity(int quantity) {
        this.quantity = quantity;
    }
}