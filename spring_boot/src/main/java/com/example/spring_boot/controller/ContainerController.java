package com.example.spring_boot.controller;

import com.example.spring_boot.entity.Container;
import com.example.spring_boot.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class ContainerController {

    private final ContainerService containerService;

    @Autowired
    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/")
    public ResponseEntity<?> createContainer(@RequestBody Container container) {
        try {
            containerService.saveContainer(container);
            return ResponseEntity.ok("Container created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating container: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public ResponseEntity<List<Container>> getAllContainers() {
        List<Container> containers = containerService.fetchContainers();
        return containers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(containers);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Container> getContainerById(@PathVariable String id) {
        Container container = containerService.fetchContainerById(id);
        return container != null ? ResponseEntity.ok(container) : ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContainer(@PathVariable String id, @RequestBody Container containerDetails) {
        try {
            containerService.updateContainer(id, containerDetails.getCapacity(), containerDetails.getQuantity(), containerDetails.getUserId());
            return ResponseEntity.ok("Container updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating container: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContainer(@PathVariable String id) {
        try {
            containerService.deleteContainer(id);
            return ResponseEntity.ok("Container deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting container: " + e.getMessage());
        }
    }
}
