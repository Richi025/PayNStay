package edu.escuelaing.PayNStay.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "appointments")
public class Appointment {
    @Id
    private UUID id;
    private UUID propertyId; 
    private UUID userId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus appointmentStatus;

    public Appointment(UUID id, UUID propertyId, UUID userId, LocalDateTime appointmentDate, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.propertyId = propertyId;
        this.userId = userId;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment() {
        //TODO Auto-generated constructor stub
    }

    public enum AppointmentStatus {
        PENDING, CONFIRMED, COMPLETED;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
    
}
