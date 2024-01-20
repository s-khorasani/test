package com.example.spring_boot.entity;

public class Plant {
    private String id;
    private String name;
    private double moisture;

    public Plant(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
    }

    //actual moisture does not have to be stored as a functional requirement
    //only the ideal moisture should be linked to the plant
    //to achieve this a plant should have a PlantProfile attribute that includes an ideal moisture attribute in turn
    public double getMoisture() {
        return this.moisture;
    }

    public void setMoisture(double moisture) {
    }
}
