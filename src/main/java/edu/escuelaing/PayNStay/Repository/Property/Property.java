package edu.escuelaing.PayNStay.Repository.Property;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(collection = "properties")
public class Property {
    @Id
    private UUID id;
    private String address;
    private String city;
    private PropertyType propertyType; 
    private BigDecimal price;
    private double size;
    private int bedrooms;
    private int bathrooms;
    private String description;
    private List<String> images; 
    private UUID ownerId; 
    private LocalDate publicationDate;
    private String virtualTourUrl; 
    private List<String> arFiles;  

    public Property(UUID id, String address, String city, PropertyType propertyType, BigDecimal price, double size,
                    int bedrooms, int bathrooms, String description, List<String> images, UUID ownerId) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.propertyType = propertyType;
        this.price = price;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.description = description;
        this.images = images;
        this.ownerId = ownerId;
        this.publicationDate = LocalDate.now();
    }

    public enum PropertyType {
        HOUSE, APARTMENT, COMMERCIAL_PROPERTY;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getVirtualTourUrl() {
        return virtualTourUrl;
    }

    public void setVirtualTourUrl(String virtualTourUrl) {
        this.virtualTourUrl = virtualTourUrl;
    }

    public List<String> getArFiles() {
        return arFiles;
    }

    public void setArFiles(List<String> arFiles) {
        this.arFiles = arFiles;
    }
    
}
