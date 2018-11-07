package com.bignerdranch.android.recycolumbus;

public class Product {
    public String name;
    public boolean isRecyclable;
    public String userThatAddedProduct;

    public Product() {

    }

    public Product(String name, boolean isRecyclable, String userThatAddedProduct) {
        this.name = name;
        this.isRecyclable = isRecyclable;
        this.userThatAddedProduct = userThatAddedProduct;
    }
}
