package com.example.shopping_cart;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class represents a shopping cart containing
 *  differnt self-explanatory methods
 **/
public class Cart {
    private List<Product_details> products;
    public Cart() {
        products = new ArrayList<Product_details>();
    }
    public void addProduct(Product_details product) {
        products.add(product);
    }
    public void removeProduct(String name) {
        for (Product_details product : products) {
            if (product.get_Name().equals(name)) {
                products.remove(product);
                break;
            }
        }
    }
    public List<Product_details> getProducts() {
        return products;
    }

}