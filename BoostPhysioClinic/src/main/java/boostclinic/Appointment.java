package boostclinic;

public class Appointment {
    private String appointmentId;
    private String physiotherapistId;
    private String treatmentType;
    private String time;
    private String status;

    public Appointment(String appointmentId, String physiotherapistId, String treatmentType, String time, String status) {
        this.appointmentId = appointmentId;
        this.physiotherapistId = physiotherapistId;
        this.treatmentType = treatmentType;
        this.time = time;
        this.status = status;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPhysiotherapistId() {
        return physiotherapistId;
    }

    public String getTreatmentType() {
        return treatmentType;
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
