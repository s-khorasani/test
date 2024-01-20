package com.example.spring_boot.model;

public class TestConnection {
    private String message;

    public TestConnection(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
