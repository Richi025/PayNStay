package edu.escuelaing.PayNStay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.PayNStay.model.Property;
import edu.escuelaing.PayNStay.service.PropertyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable UUID id) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            return ResponseEntity.ok(property);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable UUID id, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable UUID id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
