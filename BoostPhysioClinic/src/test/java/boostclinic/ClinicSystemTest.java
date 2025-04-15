package boostclinic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;





class ClinicSystemTest {
    private ClinicSystem clinicSystem;

    @BeforeEach
    void setUp() {
        clinicSystem = new ClinicSystem();
        clinicSystem.addTimeSlots();
        clinicSystem.addPhysiotherapists();
    }

    @Test
    void testAddPatient() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        assertEquals(1, clinicSystem.patientList.size());
        assertEquals("P001", clinicSystem.patientList.get(0).getPatientId());
        assertEquals("John Doe", clinicSystem.patientList.get(0).getPatientName());
    }

    @Test
    void testAddDuplicatePatient() {
        Scanner scanner1 = new Scanner("P001\nJohn Doe\n");
        Scanner scanner2 = new Scanner("P001\nJane Doe\n");
        clinicSystem.addPatient(scanner1);
        clinicSystem.addPatient(scanner2);
        assertEquals(1, clinicSystem.patientList.size());
    }

    @Test
    void testRemovePatient() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.removePatient("P001");
        assertTrue(clinicSystem.patientList.isEmpty());
    }

    @Test
    void testRemoveNonExistentPatient() {
        clinicSystem.removePatient("P999");
        assertTrue(clinicSystem.patientList.isEmpty());
    }

    @Test
    void testSearchPhysiotherapistByName() {
        clinicSystem.searchPhysiotherapistByName("Dr. Smith");
        assertEquals("Dr. Smith", clinicSystem.physiotherapistList.get(0).getName());
    }

    @Test
    void testSearchNonExistentPhysiotherapist() {
        clinicSystem.searchPhysiotherapistByName("Dr. Unknown");
        assertTrue(clinicSystem.physiotherapistList.stream().noneMatch(p -> p.getName().equalsIgnoreCase("Dr. Unknown")));
    }

    @Test
    void testBookAppointment() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.bookAppointment("P001", "S101");
        assertEquals(1, clinicSystem.bookingList.size());
        assertTrue(clinicSystem.slotList.get(0).isBooked());
    }

    @Test
    void testBookUnavailableSlot() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.bookAppointment("P001", "S101");
        clinicSystem.bookAppointment("P001", "S101");
        assertEquals(1, clinicSystem.bookingList.size());
    }

    @Test
    void testCancelAppointment() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.bookAppointment("P001", "S101");
        clinicSystem.cancelAppointment("B1");
        assertEquals("CANCELLED", clinicSystem.bookingList.get(0).getStatus());
        assertFalse(clinicSystem.slotList.get(0).isBooked());
    }

    @Test
    void testCancelNonExistentAppointment() {
        clinicSystem.cancelAppointment("B999");
        assertTrue(clinicSystem.bookingList.isEmpty());
    }

    @Test
    void testChangeBooking() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.bookAppointment("P001", "S101");
        clinicSystem.changeBooking("B1", "S102");
        assertEquals("CANCELLED", clinicSystem.bookingList.get(0).getStatus());
        assertTrue(clinicSystem.slotList.get(1).isBooked());
    }

    @Test
    void testMarkAsAttended() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.bookAppointment("P001", "S101");
        clinicSystem.markAsAttended("B1");
        assertEquals("ATTENDED", clinicSystem.bookingList.get(0).getStatus());
    }

    @Test
    void testGenerateReport() {
        Scanner scanner = new Scanner("P001\nJohn Doe\n");
        clinicSystem.addPatient(scanner);
        clinicSystem.bookAppointment("P001", "S101");
        clinicSystem.generateReport();
        assertEquals(1, clinicSystem.bookingList.size());
    }
}