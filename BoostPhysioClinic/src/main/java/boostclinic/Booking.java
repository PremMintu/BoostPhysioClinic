package boostclinic;

public class Booking {
    private String bookingId;
    private String patientId;
    private String slotId;
    private String status;

    public Booking(String bookingId, String patientId, String slotId, String status) {
        this.bookingId = bookingId;
        this.patientId = patientId;
        this.slotId = slotId;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
