package boostclinic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class BookingTest {

    @Test
    void testBookingConstructorAndGetters() {
        Booking booking = new Booking("B001", "P001", "S001", "Confirmed");

        assertEquals("B001", booking.getBookingId());
        assertEquals("P001", booking.getPatientId());
        assertEquals("S001", booking.getSlotId());
        assertEquals("Confirmed", booking.getStatus());
    }

    @Test
    void testSetStatus() {
        Booking booking = new Booking("B002", "P002", "S002", "Pending");

        booking.setStatus("Cancelled");
        assertEquals("Cancelled", booking.getStatus());
    }
}