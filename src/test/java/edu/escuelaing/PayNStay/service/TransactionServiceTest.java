package edu.escuelaing.PayNStay.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.escuelaing.PayNStay.model.Transaction;
import edu.escuelaing.PayNStay.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(1, result.size());
        verify(transactionRepository).findAll();
    }

    @Test
    void testGetTransactionById() {
        UUID transactionId = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.getTransactionById(transactionId);

        assertNotNull(result);
        verify(transactionRepository).findById(transactionId);
    }

    @Test
    void testGetTransactionByIdNotFound() {
        UUID transactionId = UUID.randomUUID();
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Transaction result = transactionService.getTransactionById(transactionId);

        assertNull(result);
        verify(transactionRepository).findById(transactionId);
    }

    @Test
    void testCreateTransaction() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = transactionService.createTransaction(transaction);

        assertNotNull(result);
        assertNotNull(result.getId()); 
        verify(transactionRepository).save(transaction);
    }

    @Test
    void testUpdateTransaction() {
        UUID transactionId = UUID.randomUUID();
        Transaction existingTransaction = new Transaction();
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setPropertyId(UUID.randomUUID());
        updatedTransaction.setBuyerId(UUID.randomUUID());
        updatedTransaction.setFinalPrice(new BigDecimal("300000"));

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(existingTransaction));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(existingTransaction);

        Transaction result = transactionService.updateTransaction(transactionId, updatedTransaction);

        assertNotNull(result);
        assertEquals(updatedTransaction.getPropertyId(), existingTransaction.getPropertyId());
        assertEquals(updatedTransaction.getBuyerId(), existingTransaction.getBuyerId());
        assertEquals(new BigDecimal("300000"), existingTransaction.getFinalPrice());
        verify(transactionRepository).findById(transactionId);
        verify(transactionRepository).save(existingTransaction);
    }

    @Test
    void testUpdateTransactionNotFound() {
        UUID transactionId = UUID.randomUUID();
        Transaction updatedTransaction = new Transaction();

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        Transaction result = transactionService.updateTransaction(transactionId, updatedTransaction);

        assertNull(result);
        verify(transactionRepository).findById(transactionId);
        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    void testDeleteTransaction() {
        UUID transactionId = UUID.randomUUID();
        doNothing().when(transactionRepository).deleteById(transactionId);

        transactionService.deleteTransaction(transactionId);

        verify(transactionRepository).deleteById(transactionId);
    }
}

