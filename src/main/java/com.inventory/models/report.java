package com.inventory.models;

public class Report {

    private String title;
    private int total;

    public Report(String title, int total) {
        this.title = title;
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public int getTotal() {
        return total;
    }
}