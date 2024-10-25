package edu.escuelaing.PayNStay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.PayNStay.model.Transaction;
import edu.escuelaing.PayNStay.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction createTransaction(Transaction transaction) {
        transaction.setId(UUID.randomUUID());
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(UUID id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setPropertyId(updatedTransaction.getPropertyId());
            transaction.setBuyerId(updatedTransaction.getBuyerId());
            transaction.setFinalPrice(updatedTransaction.getFinalPrice());
            return transactionRepository.save(transaction);
        }).orElse(null);
    }

    public void deleteTransaction(UUID id) {
        transactionRepository.deleteById(id);
    }
}
