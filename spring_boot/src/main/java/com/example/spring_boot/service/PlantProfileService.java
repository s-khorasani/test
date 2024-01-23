package com.example.spring_boot.service;

import com.example.spring_boot.entity.PlantProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantProfileService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlantProfileService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePlantProfile(PlantProfile plantProfile) {
        String sqlQuery = "INSERT INTO plant_profile (id, name, moisture) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, plantProfile.getId(), plantProfile.getName(), plantProfile.getMoisture());
    }

    public List<PlantProfile> fetchPlantProfiles() {
        String sqlQuery = "SELECT * FROM plant_profile";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
                new PlantProfile(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("moisture")
                )
        );
    }

    public PlantProfile fetchPlantProfileById(String id) {
        String sqlQuery = "SELECT * FROM plant_profile WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, (rs, rowNum) ->
                new PlantProfile(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("moisture")
                )
        );
    }

    public void updatePlantProfile(String id, String name, double moisture) {
        String sqlQuery = "UPDATE plant_profile SET name = ?, moisture = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, name, moisture, id);
    }

    public void deletePlantProfile(String id) {
        String sqlQuery = "DELETE FROM plant_profile WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
