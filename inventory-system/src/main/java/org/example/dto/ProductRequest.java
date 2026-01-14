package org.example.dto;

public class ProductRequest {

    private String name;
    private int quantity;
    private double price;
    private Long brandId;


    private String category;


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getCategory() {
        return category;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}