package com.arigoit.arigoit.model;

/**
 * Created by dhiraj on 27-02-2016.
 */

import com.arigoit.arigoit.Utils.Constants;

public class ProductInfo {

    private String name;
    private float price;

    public ProductInfo(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
