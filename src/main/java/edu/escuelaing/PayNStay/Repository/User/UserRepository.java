package edu.escuelaing.PayNStay.Repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<UserDto, UUID> {
    Optional<UserDto> findByEmail(String email);
}
