package edu.escuelaing.PayNStay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.PayNStay.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    User findByUsername(String username);
}
