package edu.escuelaing.PayNStay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.PayNStay.model.Appointment;
import edu.escuelaing.PayNStay.repository.AppointmentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(UUID id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public Appointment createAppointment(Appointment appointment) {
        appointment.setId(UUID.randomUUID());
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(UUID id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setAppointmentStatus(updatedAppointment.getAppointmentStatus());
            return appointmentRepository.save(appointment);
        }).orElse(null);
    }

    public void deleteAppointment(UUID id) {
        appointmentRepository.deleteById(id);
    }
}
