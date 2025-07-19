package com.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.ServiceEntity;
import com.template.service.ServiceManagementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Service Management", description = "APIs for managing services like rooms, events, etc.")
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceManagementService serviceService;
 
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new service", description = "Add a new service with title, description, availability, and base price.")
    @PostMapping
    public ResponseEntity<ServiceEntity> create(@RequestBody ServiceEntity service) {
        ServiceEntity created = serviceService.createService(service);
        return ResponseEntity.ok(created);
    }
    
    @Operation(summary = "Get all services", description = "Retrieve a list of all available services.")
    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAll() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }
    
    @Operation(summary = "Get service by ID", description = "Retrieve a specific service by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getById(@PathVariable String id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
  
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a service", description = "Update the details of an existing service by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> update(@PathVariable String id, @RequestBody ServiceEntity service) {
        return ResponseEntity.ok(serviceService.updateService(id, service));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a service", description = "Delete a service by its unique ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
