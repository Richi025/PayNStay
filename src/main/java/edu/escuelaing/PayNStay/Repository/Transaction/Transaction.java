package edu.escuelaing.PayNStay.Repository.Transaction;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private UUID id;
    private UUID propertyId; 
    private UUID buyerId; 
    private TransactionType transactionType; 
    private BigDecimal finalPrice;
    private LocalDate transactionDate;

    public Transaction(UUID id, UUID propertyId, UUID buyerId, TransactionType transactionType, BigDecimal finalPrice) {
        this.id = id;
        this.propertyId = propertyId;
        this.buyerId = buyerId;
        this.transactionType = transactionType;
        this.finalPrice = finalPrice;
        this.transactionDate = LocalDate.now();
    }

    public enum TransactionType {
        PURCHASE, RENT;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public UUID getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(UUID buyerId) {
        this.buyerId = buyerId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    
}
