package edu.escuelaing.PayNStay.Repository.Property;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends MongoRepository<Property, UUID> {
    List<Property> findByCity(String city);
    List<Property> findByOwnerId(UUID ownerId);
}
