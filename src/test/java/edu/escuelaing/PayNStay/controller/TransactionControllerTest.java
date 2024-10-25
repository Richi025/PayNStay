package edu.escuelaing.PayNStay.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.escuelaing.PayNStay.model.Transaction;
import edu.escuelaing.PayNStay.service.TransactionService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> transactions = Arrays.asList(new Transaction(), new Transaction());
        when(transactionService.getAllTransactions()).thenReturn(transactions);

        List<Transaction> result = transactionController.getAllTransactions();

        assertEquals(2, result.size());
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    public void testGetTransactionById() {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(transactionService.getTransactionById(id)).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.getTransactionById(id);

        assertEquals(ResponseEntity.ok(transaction), response);
        verify(transactionService, times(1)).getTransactionById(id);
    }

    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();
        when(transactionService.createTransaction(transaction)).thenReturn(transaction);

        Transaction result = transactionController.createTransaction(transaction);

        assertEquals(transaction, result);
        verify(transactionService, times(1)).createTransaction(transaction);
    }

    @Test
    public void testUpdateTransaction() {
        UUID id = UUID.randomUUID();
        Transaction transaction = new Transaction();
        when(transactionService.updateTransaction(id, transaction)).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.updateTransaction(id, transaction);

        assertEquals(ResponseEntity.ok(transaction), response);
        verify(transactionService, times(1)).updateTransaction(id, transaction);
    }

    @Test
    public void testDeleteTransaction() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = transactionController.deleteTransaction(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(transactionService, times(1)).deleteTransaction(id);
    }
}
