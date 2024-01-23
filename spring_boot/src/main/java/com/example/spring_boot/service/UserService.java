package com.example.spring_boot.service;

import com.example.spring_boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user) {
        String sqlQuery = "INSERT INTO user (id, name, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, user.getId(), user.getName(), user.getPassword());
    }

    public List<User> fetchUsers() {
        String sqlQuery = "SELECT * FROM user";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
                new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password")
                )
        );
    }

    public User fetchUserById(String id) {
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, (rs, rowNum) ->
                new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("password")
                )
        );
    }

    public void updateUser(String id, String name, String password) {
        String sqlQuery = "UPDATE user SET name = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, name, password, id);
    }

    public void deleteUser(String id) {
        String sqlQuery = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
