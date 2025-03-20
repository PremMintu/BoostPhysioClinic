package boostclinic;

import java.util.*;

public class Main {
    private static List<Physiotherapist> physiotherapists = new ArrayList<>();
    private static List<Patient> patientList = new ArrayList<>();
    private static List<TreatmentSlot> slotList = new ArrayList<>();
    public static List<TreatmentSlot> getSlotList() {
        return slotList;
    }

    private static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializePhysiotherapists();
        initializeSlots();

        while (true) {
            System.out.println("\n--- Boost Physio Clinic System ---");
            System.out.println("1. Add a new patient");
            System.out.println("2. Remove a patient");
            System.out.println("3. Search physiotherapist by name");
            System.out.println("4. Cancel an appointment");
            System.out.println("5. Change a booking");
            System.out.println("6. Mark appointment as attended");
            System.out.println("7. Generate clinic report");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1: addPatient(scanner); break;
                case 2: removePatient(scanner); break;
                case 3: searchPhysiotherapist(scanner); break;
                case 4: cancelAppointment(scanner); break;
                case 5: changeBooking(scanner); break;
                case 6: markAsAttended(scanner); break;
                case 7: generateReport(); break;
                case 8: System.exit(0); break;
                default: System.out.println("Invalid option!"); break;
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();

        boolean exists = patientList.stream().anyMatch(p -> p.getPatientId().equals(patientId));
        if (exists) {
            System.out.println("Patient with ID " + patientId + " already exists. Cannot add duplicate.");
        } else {
            patientList.add(new Patient(patientId, patientName));
            System.out.println("Patient " + patientName + " added.");
        }
    }

    private static void removePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to remove: ");
        String patientId = scanner.nextLine();
        patientList.removeIf(p -> p.getPatientId().equals(patientId));
        System.out.println("Patient with ID " + patientId + " removed.");
    }

    private static void searchPhysiotherapist(Scanner scanner) {
        System.out.println("Available Physiotherapists (ID | Name | Expertise | Address | Telephone):");
        for (Physiotherapist physio : physiotherapists) {
            System.out.println(physio.getId() + " | " + physio.getName() + " | " + physio.getExpertise() + " | " + physio.getAddress() + " | " + physio.getTelephone());
        }
        System.out.print("Enter physiotherapist's name to search: ");
        String name = scanner.nextLine();
        Physiotherapist foundPhysio = physiotherapists.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (foundPhysio != null) {
            System.out.println("Found: " + foundPhysio.getId() + " | " + foundPhysio.getName() + " | " + foundPhysio.getExpertise() + " | " + foundPhysio.getAddress() + " | " + foundPhysio.getTelephone());
        } else {
            System.out.println("Physiotherapist not found.");
        }
    }

    private static void cancelAppointment(Scanner scanner) {
        System.out.print("Enter Appointment ID to cancel: ");
        String appointmentId = scanner.nextLine();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setStatus("CANCELLED");
                System.out.println("Appointment " + appointmentId + " has been cancelled.");
                return;
            }
        }
        System.out.println("Appointment ID not found.");
    }

    private static void changeBooking(Scanner scanner) {
        System.out.print("Enter Appointment ID to change: ");
        String appointmentId = scanner.nextLine();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                System.out.print("Enter new slot ID: ");
                String slotId = scanner.nextLine();
                appointment.setStatus("Changed");
                System.out.println("Appointment " + appointmentId + " changed to new slot " + slotId);
                return;
            }
        }
        System.out.println("Appointment ID not found.");
    }

    private static void markAsAttended(Scanner scanner) {
        System.out.print("Enter Appointment ID to mark as attended: ");
        String appointmentId = scanner.nextLine();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointment.setStatus("ATTENDED");
                System.out.println("Appointment " + appointmentId + " marked as attended.");
                return;
            }
        }
        System.out.println("Appointment ID not found.");
    }

    private static void generateReport() {
        System.out.println("\n--- Clinic Report ---");
        for (Physiotherapist physio : physiotherapists) {
            System.out.println("Physiotherapist: " + physio.getName() + " (Expertise: " + physio.getExpertise() + ")");
            for (Appointment appointment : appointments) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId() +
                                   " | Treatment Name: " + appointment.getTreatmentType() +
                                   " | Patient: " + appointment.getPhysiotherapistId() +
                                   " | Status: " + appointment.getStatus());
            }
        }
    }

    private static void initializePhysiotherapists() {
        physiotherapists.add(new Physiotherapist("PH101", "Dr. William", "Rehabilitation", "123 Healthway Ave", "05412 345678"));
        physiotherapists.add(new Physiotherapist("PH102", "Dr. Olivia", "Osteopathy", "456 Recovery Rd", "06543 567890"));
        physiotherapists.add(new Physiotherapist("PH103", "Dr. Liam", "Acupuncture", "789 Wellness St", "07654 678901"));
        physiotherapists.add(new Physiotherapist("PH104", "Dr. Emma", "Rehabilitation", "101 Fitness Blvd", "08765 789012"));
        physiotherapists.add(new Physiotherapist("PH105", "Dr. Daniel", "Sports Medicine", "202 Sport Ave", "09876 890123"));
    }

    private static void initializeSlots() {
        appointments.add(new Appointment("A001", "PH101", "Rehabilitation", "2025-05-01T10:00:00", "Booked"));
        appointments.add(new Appointment("A002", "PH102", "Osteopathy", "2025-05-02T11:00:00", "Available"));
        appointments.add(new Appointment("A003", "PH103", "Acupuncture", "2025-05-03T12:00:00", "Available"));
        appointments.add(new Appointment("A004", "PH104", "Rehabilitation", "2025-05-04T13:00:00", "Cancelled"));
        appointments.add(new Appointment("A005", "PH105", "Sports Medicine", "2025-05-05T14:00:00", "Attended"));
        appointments.add(new Appointment("A006", "PH101", "Rehabilitation", "2025-05-06T15:00:00", "Available"));
        appointments.add(new Appointment("A007", "PH102", "Osteopathy", "2025-05-07T16:00:00", "Booked"));
        appointments.add(new Appointment("A008", "PH103", "Acupuncture", "2025-05-08T17:00:00", "Booked"));
        appointments.add(new Appointment("A009", "PH104", "Rehabilitation", "2025-05-09T18:00:00", "Available"));
        appointments.add(new Appointment("A010", "PH105", "Sports Medicine", "2025-05-10T19:00:00", "Attended"));
    }
}