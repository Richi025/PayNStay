package edu.escuelaing.PayNStay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.PayNStay.model.Property;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends MongoRepository<Property, UUID> {
    List<Property> findByCity(String city);
    List<Property> findByOwnerId(UUID ownerId);
}
