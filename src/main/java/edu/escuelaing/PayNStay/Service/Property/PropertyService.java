package edu.escuelaing.PayNStay.Service.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.PayNStay.Repository.Property.Property;
import edu.escuelaing.PayNStay.Repository.Property.PropertyRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(UUID id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property createProperty(Property property) {
        property.setId(UUID.randomUUID());
        return propertyRepository.save(property);
    }

    public Property updateProperty(UUID id, Property updatedProperty) {
        return propertyRepository.findById(id).map(property -> {
            property.setAddress(updatedProperty.getAddress());
            property.setCity(updatedProperty.getCity());
            property.setPrice(updatedProperty.getPrice());
            property.setBedrooms(updatedProperty.getBedrooms());
            property.setBathrooms(updatedProperty.getBathrooms());
            return propertyRepository.save(property);
        }).orElse(null);
    }

    public void deleteProperty(UUID id) {
        propertyRepository.deleteById(id);
    }
}

