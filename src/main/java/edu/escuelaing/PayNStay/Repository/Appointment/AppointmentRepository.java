package edu.escuelaing.PayNStay.Repository.Appointment;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.escuelaing.PayNStay.Repository.Appointment.Appointment.AppointmentStatus;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends MongoRepository<Appointment, UUID> {

    List<Appointment> findByPropertyId(UUID propertyId);
    List<Appointment> findByUserId(UUID userId);
    List<Appointment> findByAppointmentStatus(AppointmentStatus status);
}
