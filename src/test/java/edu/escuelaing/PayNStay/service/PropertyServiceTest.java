package edu.escuelaing.PayNStay.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import edu.escuelaing.PayNStay.model.Property;
import edu.escuelaing.PayNStay.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProperties() {
        List<Property> properties = new ArrayList<>();
        properties.add(new Property());
        when(propertyRepository.findAll()).thenReturn(properties);

        List<Property> result = propertyService.getAllProperties();

        assertEquals(1, result.size());
        verify(propertyRepository).findAll();
    }

    @Test
    void testGetPropertyById() {
        UUID propertyId = UUID.randomUUID();
        Property property = new Property();
        when(propertyRepository.findById(propertyId)).thenReturn(Optional.of(property));

        Property result = propertyService.getPropertyById(propertyId);

        assertNotNull(result);
        verify(propertyRepository).findById(propertyId);
    }

    @Test
    void testGetPropertyByIdNotFound() {
        UUID propertyId = UUID.randomUUID();
        when(propertyRepository.findById(propertyId)).thenReturn(Optional.empty());

        Property result = propertyService.getPropertyById(propertyId);

        assertNull(result);
        verify(propertyRepository).findById(propertyId);
    }

    @Test
    void testCreateProperty() {
        Property property = new Property();
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property result = propertyService.createProperty(property);

        assertNotNull(result);
        assertNotNull(result.getId()); 
        verify(propertyRepository).save(property);
    }

    @Test
    void testUpdateProperty() {
        UUID propertyId = UUID.randomUUID();
        Property existingProperty = new Property();
        Property updatedProperty = new Property();
        
        updatedProperty.setAddress("New Address");
        updatedProperty.setCity("New City");
        updatedProperty.setPrice(new BigDecimal("500000")); 
        updatedProperty.setBedrooms(4);
        updatedProperty.setBathrooms(2);

        when(propertyRepository.findById(propertyId)).thenReturn(Optional.of(existingProperty));
        when(propertyRepository.save(any(Property.class))).thenReturn(existingProperty);

        Property result = propertyService.updateProperty(propertyId, updatedProperty);

        assertNotNull(result);
        assertEquals("New Address", existingProperty.getAddress());
        assertEquals("New City", existingProperty.getCity());
        assertEquals(new BigDecimal("500000"), existingProperty.getPrice()); 
        assertEquals(4, existingProperty.getBedrooms());
        assertEquals(2, existingProperty.getBathrooms());
        verify(propertyRepository).findById(propertyId);
        verify(propertyRepository).save(existingProperty);
    }

    @Test
    void testUpdatePropertyNotFound() {
        UUID propertyId = UUID.randomUUID();
        Property updatedProperty = new Property();

        when(propertyRepository.findById(propertyId)).thenReturn(Optional.empty());

        Property result = propertyService.updateProperty(propertyId, updatedProperty);

        assertNull(result);
        verify(propertyRepository).findById(propertyId);
        verify(propertyRepository, never()).save(any(Property.class));
    }

    @Test
    void testDeleteProperty() {
        UUID propertyId = UUID.randomUUID();
        doNothing().when(propertyRepository).deleteById(propertyId);

        propertyService.deleteProperty(propertyId);

        verify(propertyRepository).deleteById(propertyId);
    }
}

