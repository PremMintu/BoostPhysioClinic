package boostclinic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class TreatmentSlotTest {

    @Test
    void testConstructorAndGetters() {
        TreatmentSlot slot = new TreatmentSlot("S1", "Physiotherapy", "10:00 AM", "Available");

        assertEquals("S1", slot.getSlotId());
        assertEquals("Physiotherapy", slot.getTreatmentType());
        assertEquals("10:00 AM", slot.getTime());
        assertEquals("Available", slot.getStatus());
        assertFalse(slot.isBooked());
    }

    @Test
    void testSetAndGetPatientId() {
        TreatmentSlot slot = new TreatmentSlot("S1", "Physiotherapy", "10:00 AM", "Available");
        slot.setPatientId("P123");

        assertEquals("P123", slot.getPatientId());
    }

    @Test
    void testSetAndGetStatus() {
        TreatmentSlot slot = new TreatmentSlot("S1", "Physiotherapy", "10:00 AM", "Available");
        slot.setStatus("Booked");

        assertEquals("Booked", slot.getStatus());
    }

    @Test
    void testSetAndGetIsBooked() {
        TreatmentSlot slot = new TreatmentSlot("S1", "Physiotherapy", "10:00 AM", "Available");
        slot.setBooked(true);

        assertTrue(slot.isBooked());
    }
}