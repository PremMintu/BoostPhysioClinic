package boostclinic;

import java.util.*;

public class ClinicSystem {
    public List<Patient> patientList = new ArrayList<>();
    public List<Physiotherapist> physiotherapistList = new ArrayList<>();
    public List<TreatmentSlot> slotList = new ArrayList<>();
    public List<Booking> bookingList = new ArrayList<>();

    // Add new patient
    public void addPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
    
        // Check if a patient with the same ID already exists
        boolean exists = patientList.stream().anyMatch(p -> p.getPatientId().equals(patientId));
        if (exists) {
            System.out.println("Error: A patient with ID " + patientId + " already exists.");
            return;
        }
    
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        patientList.add(new Patient(patientId, patientName));
        System.out.println("Patient " + patientName + " added.");
    }

    // Remove a patient
    public void removePatient(String patientId) {
        for (Patient patient : patientList) {
            if (patient.getPatientId().equals(patientId)) {
                patientList.remove(patient);
                System.out.println("Patient removed: " + patientId);
                return;
            }
        }
        System.out.println("Patient ID not found.");
    }

    // Add time slots
    public void addTimeSlots() {
        slotList.add(new TreatmentSlot("S101", "09:00 AM", "10:00 AM", null));
        slotList.add(new TreatmentSlot("S102", "10:00 AM", "11:00 AM", null));
        slotList.add(new TreatmentSlot("S103", "11:00 AM", "12:00 PM", null));
        slotList.add(new TreatmentSlot("S104", "12:00 PM", "01:00 PM", null));
        slotList.add(new TreatmentSlot("S105", "02:00 PM", "03:00 PM", null));
        slotList.add(new TreatmentSlot("S106", "03:00 PM", "04:00 PM", null));
        slotList.add(new TreatmentSlot("S107", "04:00 PM", "05:00 PM", null));
    }

    // Add some sample physiotherapists
    public void addPhysiotherapists() {
        physiotherapistList.add(new Physiotherapist("PH101", "Dr. Smith", "Rehabilitation", "123 Clinic Rd", "01234 567890"));
        physiotherapistList.add(new Physiotherapist("PH102", "Dr. Lee", "Osteopathy", "456 Health Ave", "09876 543210"));
        physiotherapistList.add(new Physiotherapist("PH103", "Dr. Johnson", "Physiotherapy", "789 Wellness St", "01111 222333"));
        physiotherapistList.add(new Physiotherapist("PH104", "Dr. Mia", "Massage Therapy", "101 Healing St", "09874 564210"));
        physiotherapistList.add(new Physiotherapist("PH105", "Dr. Emma", "Chiropractic", "202 Align Rd", "02345 678901"));
    }

    // Search physiotherapist by name
    public void searchPhysiotherapistByName(String name) {
        boolean found = false;
        System.out.println("Available Physiotherapists:");
        System.out.println("ID | Name | Expertise | Address | Telephone");
        for (Physiotherapist physio : physiotherapistList) {
            if (physio.getName().equalsIgnoreCase(name)) {
                found = true;
                System.out.println(physio.getId() + " | " + physio.getName() + " | " + physio.getExpertise() + " | " + physio.getAddress() + " | " + physio.getTelephone());
            }
        }
        if (!found) {
            System.out.println("Physiotherapist not found.");
        }
    }

    // Book an appointment
    public void bookAppointment(String patientId, String slotId) {
        TreatmentSlot slot = findSlot(slotId);
        if (slot != null && !slot.isBooked()) {
            String bookingId = "B" + (bookingList.size() + 1);
            Booking booking = new Booking(bookingId, patientId, slotId, "BOOKED");
            bookingList.add(booking);
            slot.setBooked(true);
            System.out.println("Appointment booked! Booking ID: " + bookingId);
        } else {
            System.out.println("Slot is either unavailable or already booked.");
        }
    }

    // Cancel an appointment
    public void cancelAppointment(String bookingId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId().equals(bookingId)) {
                booking.setStatus("CANCELLED");
                TreatmentSlot slot = findSlot(booking.getSlotId());
                if (slot != null) {
                    slot.setBooked(false);
                }
                System.out.println("Booking cancelled: " + bookingId);
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    // Change an appointment (cancel and re-book)
    public void changeBooking(String oldBookingId, String newSlotId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId().equals(oldBookingId)) {
                booking.setStatus("CANCELLED");
                TreatmentSlot slot = findSlot(booking.getSlotId());
                if (slot != null) {
                    slot.setBooked(false);
                }
                bookAppointment(booking.getPatientId(), newSlotId);
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    // Mark appointment as attended
    public void markAsAttended(String bookingId) {
        for (Booking booking : bookingList) {
            if (booking.getBookingId().equals(bookingId)) {
                booking.setStatus("ATTENDED");
                System.out.println("Appointment attended: " + bookingId);
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    // Print final clinic report
    public void generateReport() {
        System.out.println("Final Report:");
        for (Booking booking : bookingList) {
            System.out.println("Booking ID: " + booking.getBookingId() + ", Patient ID: " + booking.getPatientId() + ", Slot ID: " + booking.getSlotId() + ", Status: " + booking.getStatus());
        }
    }

    // Helper method to find slot by ID
    private TreatmentSlot findSlot(String slotId) {
        for (TreatmentSlot slot : slotList) {
            if (slot.getSlotId().equals(slotId)) {
                return slot;
            }
        }
        return null;
    }
}
