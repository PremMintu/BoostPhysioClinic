package boostclinic;

public class TreatmentSlot {
    private String slotId;
    private String treatmentType;
    private String patientId;
    private String time;
    private String status;
    private boolean isBooked;  // Add this field

    // Constructor
    public TreatmentSlot(String slotId, String treatmentType, String time, String status) {
        this.slotId = slotId;
        this.treatmentType = treatmentType;
        this.time = time;
        this.status = status;
        this.isBooked = false; // default value
    }

    // Getter and Setter for isBooked
    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    // Other getters and setters
    public String getSlotId() {
        return slotId;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
