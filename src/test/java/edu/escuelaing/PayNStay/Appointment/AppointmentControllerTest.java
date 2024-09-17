package edu.escuelaing.PayNStay.Appointment;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.escuelaing.PayNStay.Controller.Appointment.AppointmentController;
import edu.escuelaing.PayNStay.Repository.Appointment.Appointment;
import edu.escuelaing.PayNStay.Service.Appointment.AppointmentService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAppointments() {
        List<Appointment> appointments = Arrays.asList(new Appointment(), new Appointment());
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        List<Appointment> result = appointmentController.getAllAppointments();

        assertEquals(2, result.size());
        verify(appointmentService, times(1)).getAllAppointments();
    }

    @Test
    public void testGetAppointmentById() {
        UUID id = UUID.randomUUID();
        Appointment appointment = new Appointment();
        when(appointmentService.getAppointmentById(id)).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(id);

        assertEquals(ResponseEntity.ok(appointment), response);
        verify(appointmentService, times(1)).getAppointmentById(id);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentService.createAppointment(appointment)).thenReturn(appointment);

        Appointment result = appointmentController.createAppointment(appointment);

        assertEquals(appointment, result);
        verify(appointmentService, times(1)).createAppointment(appointment);
    }

    @Test
    public void testUpdateAppointment() {
        UUID id = UUID.randomUUID();
        Appointment appointment = new Appointment();
        when(appointmentService.updateAppointment(id, appointment)).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.updateAppointment(id, appointment);

        assertEquals(ResponseEntity.ok(appointment), response);
        verify(appointmentService, times(1)).updateAppointment(id, appointment);
    }

    @Test
    public void testDeleteAppointment() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = appointmentController.deleteAppointment(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(appointmentService, times(1)).deleteAppointment(id);
    }
}
