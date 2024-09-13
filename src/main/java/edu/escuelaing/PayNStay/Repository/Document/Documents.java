package edu.escuelaing.PayNStay.Repository.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document(collection = "documents")
public class Documents {
    @Id
    private UUID id;
    private UUID propertyId; 
    private DocumentType documentType; 
    private String documentUrl;

    public Documents(UUID id, UUID propertyId, DocumentType documentType, String documentUrl) {
        this.id = id;
        this.propertyId = propertyId;
        this.documentType = documentType;
        this.documentUrl = documentUrl;
    }

    public enum DocumentType {
        CONTRACT, PLANS, PERMITS;
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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
    
}
