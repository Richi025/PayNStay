package edu.escuelaing.PayNStay.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import edu.escuelaing.PayNStay.model.Appointment;
import edu.escuelaing.PayNStay.model.Appointment.AppointmentStatus;
import edu.escuelaing.PayNStay.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        when(appointmentRepository.findAll()).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAllAppointments();

        assertEquals(1, result.size());
        verify(appointmentRepository).findAll();
    }

    @Test
    void testGetAppointmentById() {
        UUID appointmentId = UUID.randomUUID();
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        Appointment result = appointmentService.getAppointmentById(appointmentId);

        assertNotNull(result);
        verify(appointmentRepository).findById(appointmentId);
    }

    @Test
    void testGetAppointmentById_NotFound() {
        UUID appointmentId = UUID.randomUUID();
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        Appointment result = appointmentService.getAppointmentById(appointmentId);

        assertNull(result);
        verify(appointmentRepository).findById(appointmentId);
    }

    @Test
    void testCreateAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.createAppointment(appointment);

        assertNotNull(result);
        assertNotNull(result.getId()); 
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void testUpdateAppointment() {
        UUID appointmentId = UUID.randomUUID();
        Appointment existingAppointment = new Appointment();
        Appointment updatedAppointment = new Appointment();
        
        updatedAppointment.setAppointmentDate(LocalDateTime.of(2024, 12, 1, 10, 0));
        updatedAppointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(existingAppointment);

        Appointment result = appointmentService.updateAppointment(appointmentId, updatedAppointment);

        assertNotNull(result);
        assertEquals(LocalDateTime.of(2024, 12, 1, 10, 0), existingAppointment.getAppointmentDate());
        assertEquals(AppointmentStatus.CONFIRMED, existingAppointment.getAppointmentStatus());
        verify(appointmentRepository).findById(appointmentId);
        verify(appointmentRepository).save(existingAppointment);
    }

    @Test
    void testUpdateAppointmentNotFound() {
        UUID appointmentId = UUID.randomUUID();
        Appointment updatedAppointment = new Appointment();

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        Appointment result = appointmentService.updateAppointment(appointmentId, updatedAppointment);

        assertNull(result);
        verify(appointmentRepository).findById(appointmentId);
        verify(appointmentRepository, never()).save(any(Appointment.class));
    }

    @Test
    void testDeleteAppointment() {
        UUID appointmentId = UUID.randomUUID();
        doNothing().when(appointmentRepository).deleteById(appointmentId);

        appointmentService.deleteAppointment(appointmentId);

        verify(appointmentRepository).deleteById(appointmentId);
    }
}
