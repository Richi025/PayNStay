package edu.escuelaing.PayNStay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.PayNStay.model.Documents;
import edu.escuelaing.PayNStay.repository.DocumentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Documents> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Documents getDocumentById(UUID id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Documents createDocument(Documents document) {
        document.setId(UUID.randomUUID());
        return documentRepository.save(document);
    }

    public Documents updateDocument(UUID id, Documents updatedDocument) {
        return documentRepository.findById(id).map(document -> {
            document.setDocumentUrl(updatedDocument.getDocumentUrl());
            document.setDocumentType(updatedDocument.getDocumentType());
            return documentRepository.save(document);
        }).orElse(null);
    }

    public void deleteDocument(UUID id) {
        documentRepository.deleteById(id);
    }
}
