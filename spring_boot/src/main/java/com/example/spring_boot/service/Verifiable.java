package com.example.spring_boot.service;

import java.sql.Connection;

public interface Verifiable {
    void checkArguments(String string, Connection connection);
}
