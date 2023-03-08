package com.example.prm_app_shopping.model;

public class Cart {
    private int Id;
    private double Total;
    private Product product;

    public Cart(int id, double total, Product product) {
        Id = id;
        Total = total;
        this.product = product;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
