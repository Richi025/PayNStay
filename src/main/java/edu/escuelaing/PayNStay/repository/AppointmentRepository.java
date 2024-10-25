package edu.escuelaing.PayNStay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.PayNStay.model.Appointment;
import edu.escuelaing.PayNStay.model.Appointment.AppointmentStatus;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends MongoRepository<Appointment, UUID> {

    List<Appointment> findByPropertyId(UUID propertyId);
    List<Appointment> findByUserId(UUID userId);
    List<Appointment> findByAppointmentStatus(AppointmentStatus status);
}
