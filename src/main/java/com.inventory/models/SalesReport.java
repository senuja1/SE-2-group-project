package com.inventory.models;

import java.sql.Date;

public class SalesReport {

    private int saleId;
    private String itemName;
    private int quantity;
    private double totalAmount;
    private Date saleDate;

    public SalesReport(int saleId, String itemName, int quantity, double totalAmount, Date saleDate) {
        this.saleId = saleId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.saleDate = saleDate;
    }

    public int getSaleId() { return saleId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public double getTotalAmount() { return totalAmount; }
    public Date getSaleDate() { return saleDate; }
}