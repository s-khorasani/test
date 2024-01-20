package com.example.spring_boot.service;

import com.example.spring_boot.entity.Container;
import java.sql.*;
import java.util.ArrayList;

public class ContainerService implements Verifiable {

    @Override
    public void checkArguments(String string, Connection connection) {
        if (connection == null || string == null || string.isEmpty()) {
            throw new NullPointerException("Arguments are null or empty");
        }
    }

    public void saveContainer(double capacity, double quantity, String userId, Connection connection) {
        checkArguments(userId, connection);
        String sqlQuery = "INSERT INTO containers (capacity, quantity, userid) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setDouble(1, capacity);
            statement.setDouble(2, quantity);
            statement.setString(3, userId);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Save complete" : "Save failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while registering a container: " + e);
        }
    }

    public ArrayList<Container> fetchContainers(Connection connection) {
        ArrayList<Container> containers = new ArrayList<>();

        String sqlQuery = "SELECT * FROM containers";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                double capacity = resultSet.getDouble("capacity");
                double quantity = resultSet.getDouble("quantity");
                String userId = resultSet.getString("userid");
                containers.add(new Container(capacity, quantity, userId));
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching containers: " + e);
        }
        return containers;
    }


}
