package edu.escuelaing.PayNStay.Repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    User findByEmail(String email);
}
