package boostclinic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AppointmentTest {

    @Test
    void testAppointmentConstructorAndGetters() {
        Appointment appointment = new Appointment("A001", "P001", "Physiotherapy", "10:00 AM", "Scheduled");

        assertEquals("A001", appointment.getAppointmentId());
        assertEquals("P001", appointment.getPhysiotherapistId());
        assertEquals("Physiotherapy", appointment.getTreatmentType());
        assertEquals("10:00 AM", appointment.getTime());
        assertEquals("Scheduled", appointment.getStatus());
    }

    @Test
    void testSetStatus() {
        Appointment appointment = new Appointment("A002", "P002", "Massage", "11:00 AM", "Scheduled");

        appointment.setStatus("Completed");
        assertEquals("Completed", appointment.getStatus());
    }
}