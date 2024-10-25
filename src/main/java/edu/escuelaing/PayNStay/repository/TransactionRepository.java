package edu.escuelaing.PayNStay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.PayNStay.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends MongoRepository<Transaction, UUID> {
    List<Transaction> findByPropertyId(UUID propertyId);
    List<Transaction> findByBuyerId(UUID buyerId);
}
