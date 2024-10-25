package edu.escuelaing.PayNStay.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import edu.escuelaing.PayNStay.model.Documents;
import edu.escuelaing.PayNStay.model.Documents.DocumentType;
import edu.escuelaing.PayNStay.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllDocuments() {
        List<Documents> documents = new ArrayList<>();
        documents.add(new Documents());
        when(documentRepository.findAll()).thenReturn(documents);

        List<Documents> result = documentService.getAllDocuments();

        assertEquals(1, result.size());
        verify(documentRepository).findAll();
    }

    @Test
    void testGetDocumentById() {
        UUID documentId = UUID.randomUUID();
        Documents document = new Documents();
        when(documentRepository.findById(documentId)).thenReturn(Optional.of(document));

        Documents result = documentService.getDocumentById(documentId);

        assertNotNull(result);
        verify(documentRepository).findById(documentId);
    }

    @Test
    void testGetDocumentByIdNotFound() {
        UUID documentId = UUID.randomUUID();
        when(documentRepository.findById(documentId)).thenReturn(Optional.empty());

        Documents result = documentService.getDocumentById(documentId);

        assertNull(result);
        verify(documentRepository).findById(documentId);
    }

    @Test
    void testCreateDocument() {
        Documents document = new Documents();
        when(documentRepository.save(any(Documents.class))).thenReturn(document);

        Documents result = documentService.createDocument(document);

        assertNotNull(result);
        assertNotNull(result.getId()); 
        verify(documentRepository).save(document);
    }

    @Test
    void testUpdateDocument() {
        UUID documentId = UUID.randomUUID();
        Documents existingDocument = new Documents();
        Documents updatedDocument = new Documents();
        
        updatedDocument.setDocumentUrl("http://newdocumenturl.com");
        updatedDocument.setDocumentType(DocumentType.CONTRACT); 

        when(documentRepository.findById(documentId)).thenReturn(Optional.of(existingDocument));
        when(documentRepository.save(any(Documents.class))).thenReturn(existingDocument);


        Documents result = documentService.updateDocument(documentId, updatedDocument);

        assertNotNull(result);
        assertEquals("http://newdocumenturl.com", existingDocument.getDocumentUrl());
        assertEquals(DocumentType.CONTRACT, existingDocument.getDocumentType());
        verify(documentRepository).findById(documentId);
        verify(documentRepository).save(existingDocument);
    }

    @Test
    void testUpdateDocumentNotFound() {
        UUID documentId = UUID.randomUUID();
        Documents updatedDocument = new Documents();

        when(documentRepository.findById(documentId)).thenReturn(Optional.empty());

        Documents result = documentService.updateDocument(documentId, updatedDocument);

        assertNull(result);
        verify(documentRepository).findById(documentId);
        verify(documentRepository, never()).save(any(Documents.class));
    }

    @Test
    void testDeleteDocument() {
        UUID documentId = UUID.randomUUID();
        doNothing().when(documentRepository).deleteById(documentId);

        documentService.deleteDocument(documentId);

        verify(documentRepository).deleteById(documentId);
    }
}
