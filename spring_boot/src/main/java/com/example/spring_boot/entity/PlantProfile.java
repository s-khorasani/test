package com.example.spring_boot.entity;

public class PlantProfile {
    private String id;
    private String name;
    private double moisture;

    public PlantProfile() {
    }

    public PlantProfile(String id, String name, double moisture) {
        this.id = id;
        this.name = name;
        this.moisture = moisture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }
}
