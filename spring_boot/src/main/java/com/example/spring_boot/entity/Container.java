package com.example.spring_boot.entity;

public class Container {
    private String id;
    private double capacity;
    private double quantity;
    private String userId; // Foreign key reference to User

    public Container() {
    }

    public Container(double capacity, double quantity, String userId) {
        this.capacity = capacity;
        this.quantity = quantity;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
