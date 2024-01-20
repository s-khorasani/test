package com.example.spring_boot.service;

import com.example.spring_boot.entity.PlantProfile;
import java.sql.*;
import java.util.ArrayList;

public class PlantProfileService implements Verifiable {

    @Override
    public void checkArguments(String string, Connection connection) {
        if (connection == null || string == null || string.isEmpty()) {
            throw new NullPointerException("Arguments are null or empty");
        }
    }

    public void savePlantProfile(String name, double moisture, Connection connection) {
        checkArguments(name, connection);
        String sqlQuery = "INSERT INTO plant_profiles (name, moisture) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            statement.setDouble(2, moisture);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Save complete" : "Save failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while registering a plant profile: " + e);
        }
    }

    public ArrayList<PlantProfile> fetchPlantProfiles(Connection connection) {
        ArrayList<PlantProfile> plantProfiles = new ArrayList<>();

        String sqlQuery = "SELECT * FROM plant_profiles";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                double moisture = resultSet.getDouble("moisture");
                plantProfiles.add(new PlantProfile(id, name, moisture));
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching plant profiles: " + e);
        }
        return plantProfiles;
    }

    public PlantProfile fetchPlantProfileById(String id, Connection connection) {
        checkArguments(id, connection);
        String sqlQuery = "SELECT * FROM plant_profiles WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double moisture = resultSet.getDouble("moisture");
                    return new PlantProfile(id, name, moisture);
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching plant profile " + id + ": " + e);
        }
        return null; // Or throw an exception?
    }

    public void updatePlantProfile(String id, String name, double moisture, Connection connection) {
        checkArguments(id, connection);
        String sqlQuery = "UPDATE plant_profiles SET name = ?, moisture = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, name);
            statement.setDouble(2, moisture);
            statement.setString(3, id);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Update successful" : "Update failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while updating plant profile " + id + ": " + e);
        }
    }

    public void deletePlantProfile(String id, Connection connection) {
        checkArguments(id, connection);
        String sqlQuery = "DELETE FROM plant_profiles WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, id);

            int result = statement.executeUpdate();
            System.out.println(result == 1 ? "Deletion successful" : "Deletion failed");
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting plant profile " + id + ": " + e);
        }
    }
}
