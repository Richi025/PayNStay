package edu.escuelaing.PayNStay.Repository.Transaction;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends MongoRepository<Transaction, UUID> {
    List<Transaction> findByPropertyId(UUID propertyId);
    List<Transaction> findByBuyerId(UUID buyerId);
}
