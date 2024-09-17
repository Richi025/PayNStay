package edu.escuelaing.PayNStay.Document;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.escuelaing.PayNStay.Controller.Document.DocumentController;
import edu.escuelaing.PayNStay.Repository.Document.Documents;
import edu.escuelaing.PayNStay.Service.Document.DocumentService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DocumentControllerTest {

    @InjectMocks
    private DocumentController documentController;

    @Mock
    private DocumentService documentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDocuments() {
        List<Documents> documents = Arrays.asList(new Documents(), new Documents());
        when(documentService.getAllDocuments()).thenReturn(documents);

        List<Documents> result = documentController.getAllDocuments();

        assertEquals(2, result.size());
        verify(documentService, times(1)).getAllDocuments();
    }

    @Test
    public void testGetDocumentById() {
        UUID id = UUID.randomUUID();
        Documents document = new Documents();
        when(documentService.getDocumentById(id)).thenReturn(document);

        ResponseEntity<Documents> response = documentController.getDocumentById(id);

        assertEquals(ResponseEntity.ok(document), response);
        verify(documentService, times(1)).getDocumentById(id);
    }

    @Test
    public void testCreateDocument() {
        Documents document = new Documents();
        when(documentService.createDocument(document)).thenReturn(document);

        Documents result = documentController.createDocument(document);

        assertEquals(document, result);
        verify(documentService, times(1)).createDocument(document);
    }

    @Test
    public void testUpdateDocument() {
        UUID id = UUID.randomUUID();
        Documents document = new Documents();
        when(documentService.updateDocument(id, document)).thenReturn(document);

        ResponseEntity<Documents> response = documentController.updateDocument(id, document);

        assertEquals(ResponseEntity.ok(document), response);
        verify(documentService, times(1)).updateDocument(id, document);
    }

    @Test
    public void testDeleteDocument() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = documentController.deleteDocument(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(documentService, times(1)).deleteDocument(id);
    }
}

