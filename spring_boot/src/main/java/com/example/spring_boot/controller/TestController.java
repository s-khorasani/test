package com.example.spring_boot.controller;

import com.example.spring_boot.model.TestConnection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public TestConnection getTestConnection() {
        return new TestConnection("This is a golden nugget from the backend!");
    }
}
