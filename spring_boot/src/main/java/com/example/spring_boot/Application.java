package com.example.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    // Sorena: Hier hebben we mysql, maar in de pom.xml staat bij de dependancies mssql, klopt dit?
    // do these methods belong here, or in service? if yes, what should the class name be?
//    public static Connection getConnection() {
//        Connection con = null;
//        String connection = "jdbc:mysql://localhost:3306/dbo";
//
//        //wifi van school is beveiligd dus: gebruik eigen hotspot, of neem een backup router mee
//        String username = "root";
//        String password = "admin";
//
//        try {
//            con = DriverManager.getConnection(connection, username, password);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return con;
//    }
//
//    public static void closeConnection(Connection con) {
//        if(con != null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//    }
}
