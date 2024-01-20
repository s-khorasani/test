package com.example.spring_boot.controller;

import com.example.spring_boot.entity.Plant;
import com.example.spring_boot.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/")
    public ResponseEntity<?> createPlant(@RequestBody Plant plant) {
        try {
            plantService.savePlant(plant);
            return ResponseEntity.ok("Plant created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating plant: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public ResponseEntity<List<Plant>> getAllPlants() {
        List<Plant> plants = plantService.fetchPlants();
        return plants.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plants);
    }

//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping("/{id}")
//    public ResponseEntity<Plant> getPlantById(@PathVariable String id) {
//        Plant plant = plantService.fetchPlantById(id);
//        return plant != null ? ResponseEntity.ok(plant) : ResponseEntity.notFound().build();
//    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlant(@PathVariable String id, @RequestBody Plant plantDetails) {
        try {
            plantService.updatePlant(id, plantDetails.getName());
            return ResponseEntity.ok("Plant updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating plant: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlant(@PathVariable String id) {
        try {
            plantService.deletePlant(id);
            return ResponseEntity.ok("Plant deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting plant: " + e.getMessage());
        }
    }
}
