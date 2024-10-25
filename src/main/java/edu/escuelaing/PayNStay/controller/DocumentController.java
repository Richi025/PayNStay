package edu.escuelaing.PayNStay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.PayNStay.model.Documents;
import edu.escuelaing.PayNStay.service.DocumentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<Documents> getAllDocuments() {
        return documentService.getAllDocuments();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Documents> getDocumentById(@PathVariable UUID id) {
        Documents document = documentService.getDocumentById(id);
        if (document != null) {
            return ResponseEntity.ok(document);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Documents createDocument(@RequestBody Documents document) {
        return documentService.createDocument(document);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documents> updateDocument(@PathVariable UUID id, @RequestBody Documents document) {
        Documents updatedDocument = documentService.updateDocument(id, document);
        if (updatedDocument != null) {
            return ResponseEntity.ok(updatedDocument);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable UUID id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
