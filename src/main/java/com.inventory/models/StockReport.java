package com.inventory.models;

public class StockReport {

    private int itemId;
    private String itemName;
    private int quantity;
    private String status;

    public StockReport(int itemId, String itemName, int quantity, String status) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.status = status;
    }

    public int getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
}