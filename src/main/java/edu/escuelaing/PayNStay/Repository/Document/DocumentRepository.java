package edu.escuelaing.PayNStay.Repository.Document;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.PayNStay.Repository.Document.Documents.DocumentType;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends MongoRepository<Documents, UUID> {
    List<Documents> findByPropertyId(UUID propertyId);
    List<Documents> findByDocumentType(DocumentType documentType);
}
