package com.example.spring_boot.service;

import com.example.spring_boot.entity.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlantService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void savePlant(Plant plant) {
        String sqlQuery = "INSERT INTO plant (name) VALUES (?)";
        jdbcTemplate.update(sqlQuery, plant.getName());
    }

    public List<Plant> fetchPlants() {
        String sqlQuery = "SELECT * FROM plant";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
                new Plant(
                        rs.getString("id"),
                        rs.getString("name")
                )
        );
    }

    public Plant fetchPlantById(String id) {
        String sqlQuery = "SELECT * FROM plant WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, (rs, rowNum) ->
                new Plant(
                        rs.getString("id"),
                        rs.getString("name")
                )
        );
    }

    public void updatePlant(String id, String name) {
        String sqlQuery = "UPDATE plant SET name = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, name, id);
    }

    public void deletePlant(String id) {
        String sqlQuery = "DELETE FROM plant WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
