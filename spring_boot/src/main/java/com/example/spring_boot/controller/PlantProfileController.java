package com.example.spring_boot.controller;

import com.example.spring_boot.entity.PlantProfile;
import com.example.spring_boot.service.PlantProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plant_profiles")
public class PlantProfileController {

    private final PlantProfileService plantProfileService;

    @Autowired
    public PlantProfileController(PlantProfileService plantProfileService) {
        this.plantProfileService = plantProfileService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/")
    public ResponseEntity<?> createPlantProfile(@RequestBody PlantProfile plantProfile) {
        try {
            plantProfileService.savePlantProfile(plantProfile);
            return ResponseEntity.ok("PlantProfile created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating plant profile: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public ResponseEntity<List<PlantProfile>> getAllPlantProfiles() {
        List<PlantProfile> plantProfiles = plantProfileService.fetchPlantProfiles();
        return plantProfiles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(plantProfiles);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<PlantProfile> getPlantProfileById(@PathVariable String id) {
        PlantProfile plantProfile = plantProfileService.fetchPlantProfileById(id);
        return plantProfile != null ? ResponseEntity.ok(plantProfile) : ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlantProfile(@PathVariable String id, @RequestBody PlantProfile plantProfileDetails) {
        try {
            plantProfileService.updatePlantProfile(id, plantProfileDetails.getName(), plantProfileDetails.getMoisture());
            return ResponseEntity.ok("PlantProfile updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating plant profile: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlantProfile(@PathVariable String id) {
        try {
            plantProfileService.deletePlantProfile(id);
            return ResponseEntity.ok("PlantProfile deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting plant profile: " + e.getMessage());
        }
    }
}
