package com.example.spring_boot.service;

import com.example.spring_boot.entity.Plant;

import java.sql.*;
import java.util.ArrayList;

public class PlantService implements Verifiable {
    @Override
    public void checkArguments(String string, Connection connection) {
        if (connection == null || string == null || string.isEmpty()) {
            throw new NullPointerException("Arguments are null or empty");
        }
    }

    public void savePlant(String name, Connection connection) {
        checkArguments(name, connection);
        String sqlQuery = "INSERT INTO plant (name) VALUES (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, name);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Save complete" : "Save failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while registering a plant" + e);
        }
    }

    public ArrayList<Plant> fetchPlants(Connection connection) {
        ArrayList<Plant> plants = new ArrayList<Plant>();

        if (connection != null) {
            String sqlQuery = "SELECT * FROM plant";

            try {
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery(sqlQuery);

                while (resultset.next()) {
                    String id = resultset.getString("id");
                    String name = resultset.getString("name");
                    plants.add(new Plant(id, name));
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while fetching plants" + e);
            }
            return plants;
        } else {
            throw new NullPointerException("Connection could not be established");
        }
    }

    public Plant fetchPlantById(String id, Connection connection) {
        checkArguments(id, connection);
        String sqlQuery = "SELECT * FROM plant WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, id);

            ResultSet resultset = statement.executeQuery();
            String name = resultset.getString("name");

            return new Plant(id, name);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching plant " + id + e);
        }
        throw new AssertionError("SQLException should be declared in case the try-catch block fails");
    }

    public void updatePlant(String id, String name, Connection connection) {
        checkArguments(id, connection);
        checkArguments(name, connection);
        String sqlQuery = "UPDATE plant SET name = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, name);
            statement.setString(2, id);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Update successful" : "Update failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while updating plant " + id + e);
        }
    }

    public void deletePlant(String id, Connection connection) {
        checkArguments(id, connection);
        String sqlQuery = "DELETE FROM plant WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, id);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Deletion successful" : "Deletion failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting plant " + id + e);
        }
    }
}
