package edu.escuelaing.PayNStay.Property;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.escuelaing.PayNStay.Controller.Property.PropertyController;
import edu.escuelaing.PayNStay.Repository.Property.Property;
import edu.escuelaing.PayNStay.Service.Property.PropertyService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProperties() {
        List<Property> properties = Arrays.asList(new Property(), new Property());
        when(propertyService.getAllProperties()).thenReturn(properties);

        List<Property> result = propertyController.getAllProperties();

        assertEquals(2, result.size());
        verify(propertyService, times(1)).getAllProperties();
    }

    @Test
    public void testGetPropertyById() {
        UUID id = UUID.randomUUID();
        Property property = new Property();
        when(propertyService.getPropertyById(id)).thenReturn(property);

        ResponseEntity<Property> response = propertyController.getPropertyById(id);

        assertEquals(ResponseEntity.ok(property), response);
        verify(propertyService, times(1)).getPropertyById(id);
    }

    @Test
    public void testCreateProperty() {
        Property property = new Property();
        when(propertyService.createProperty(property)).thenReturn(property);

        Property result = propertyController.createProperty(property);

        assertEquals(property, result);
        verify(propertyService, times(1)).createProperty(property);
    }

    @Test
    public void testUpdateProperty() {
        UUID id = UUID.randomUUID();
        Property property = new Property();
        when(propertyService.updateProperty(id, property)).thenReturn(property);

        ResponseEntity<Property> response = propertyController.updateProperty(id, property);

        assertEquals(ResponseEntity.ok(property), response);
        verify(propertyService, times(1)).updateProperty(id, property);
    }

    @Test
    public void testDeleteProperty() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = propertyController.deleteProperty(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(propertyService, times(1)).deleteProperty(id);
    }
}

