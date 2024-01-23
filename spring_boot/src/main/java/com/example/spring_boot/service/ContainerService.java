package com.example.spring_boot.service;

import com.example.spring_boot.entity.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContainerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveContainer(Container container) {
        String sqlQuery = "INSERT INTO container (capacity, quantity, userId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, container.getCapacity(), container.getQuantity(), container.getUserId());
    }

    public List<Container> fetchContainers() {
        String sqlQuery = "SELECT * FROM container";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
                new Container(
                        rs.getDouble("capacity"),
                        rs.getDouble("quantity"),
                        rs.getString("userId")
                )
        );
    }

    public Container fetchContainerById(String id) {
        String sqlQuery = "SELECT * FROM container WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, (rs, rowNum) ->
                new Container(
                        rs.getDouble("capacity"),
                        rs.getDouble("quantity"),
                        rs.getString("userId")
                )
        );
    }

    public void updateContainer(String id, double capacity, double quantity, String userId) {
        String sqlQuery = "UPDATE container SET capacity = ?, quantity = ?, userId = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, capacity, quantity, userId, id);
    }

    public void deleteContainer(String id) {
        String sqlQuery = "DELETE FROM container WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
