import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize some physiotherapists, patients, and treatments (7 physiotherapists)
        Physiotherapist physio1 = new Physiotherapist(1, "Dr. Helen", "Address 1", "1234567890");
        Treatment treatment1 = new Treatment("Massage", "1 hour", "Relaxing treatment for muscles.");
        physio1.addTreatment(treatment1);

        Physiotherapist physio2 = new Physiotherapist(2, "Dr. John", "Address 2", "9876543210");
        Treatment treatment2 = new Treatment("Acupuncture", "45 minutes", "Traditional needle therapy.");
        physio2.addTreatment(treatment2);

        Physiotherapist physio3 = new Physiotherapist(3, "Dr. Alice", "Address 3", "3216549870");
        Treatment treatment3 = new Treatment("Rehabilitation", "2 hours", "Physical therapy for recovery.");
        physio3.addTreatment(treatment3);

        Physiotherapist physio4 = new Physiotherapist(4, "Dr. Mike", "Address 4", "8765432109");
        Treatment treatment4 = new Treatment("Osteopathy", "1.5 hours", "Joint manipulation and massage.");
        physio4.addTreatment(treatment4);

        Physiotherapist physio5 = new Physiotherapist(5, "Dr. Sarah", "Address 5", "6543219870");
        Treatment treatment5 = new Treatment("Chiropractic", "1 hour", "Spinal alignment.");
        physio5.addTreatment(treatment5);

        Physiotherapist physio6 = new Physiotherapist(6, "Dr. Max", "Address 6", "2345678901");
        Treatment treatment6 = new Treatment("Sports Therapy", "45 minutes", "Treatment for muscle strains.");
        physio6.addTreatment(treatment6);

        Physiotherapist physio7 = new Physiotherapist(7, "Dr. Emma", "Address 7", "1098765432");
        Treatment treatment7 = new Treatment("Massage Therapy", "1 hour", "Relaxing muscle treatment.");
        physio7.addTreatment(treatment7);

        // Create the booking system
        BookingSystem bookingSystem = new BookingSystem();
        bookingSystem.addPhysiotherapist(physio1);
        bookingSystem.addPhysiotherapist(physio2);
        bookingSystem.addPhysiotherapist(physio3);
        bookingSystem.addPhysiotherapist(physio4);
        bookingSystem.addPhysiotherapist(physio5);
        bookingSystem.addPhysiotherapist(physio6);
        bookingSystem.addPhysiotherapist(physio7);

        // Main loop for interactive system
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n---- Boost Physio Clinic ----");
            System.out.println("1. Add a patient");
            System.out.println("2. Book an appointment");
            System.out.println("3. Cancel an appointment");
            System.out.println("4. Change an appointment");
            System.out.println("5. Attend a treatment appointment");
            System.out.println("6. Print Report");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Add a new patient
                    System.out.println("Adding a new patient...");
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter Patient Address: ");
                    String patientAddress = scanner.nextLine();

                    String patientPhone;
                    while (true) {
                        System.out.print("Enter Patient Phone: ");
                        patientPhone = scanner.nextLine();

                        // Validate phone number
                        if (patientPhone.length() == 11 && patientPhone.matches("\\d+")) {
                            break;  // Valid phone number, exit the loop
                        } else {
                            System.out.println("Invalid phone number. Please ensure it's 11 digits.");
                        }
                    }

                    // Call the addPatient method in BookingSystem
                    bookingSystem.addPatient(patientName, patientAddress, patientPhone);
                    break;

                case 2:
                    // Book an appointment
                    System.out.println("Booking an appointment...");
                    System.out.print("Enter Patient ID: ");
                    int patientID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.println("\nList of available physiotherapists:");
                    for (Physiotherapist physiotherapist : bookingSystem.getPhysiotherapists()) {
                        System.out.println("ID: " + physiotherapist.getID() + ", Name: " + physiotherapist.getName());
                    }

                    System.out.print("Enter Physiotherapist ID: ");
                    int physioID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Physiotherapist physiotherapist = bookingSystem.getPhysiotherapistByID(physioID);
                    if (physiotherapist == null) {
                        System.out.println("Invalid Physiotherapist ID. Try again.");
                        break;
                    }

                    System.out.println("\nAvailable treatments for " + physiotherapist.getName() + ":");
                    for (Treatment treatment : physiotherapist.getAvailableTreatments()) {
                        System.out.println("Treatment: " + treatment.getTreatmentDetails());
                    }

                    System.out.print("Enter Treatment Name: ");
                    String treatmentName = scanner.nextLine();
                    System.out.print("Enter Appointment Time (YYYY-MM-DD HH:MM): ");
                    String time = scanner.nextLine();

                    Treatment selectedTreatment = null;
                    for (Treatment t : physiotherapist.getAvailableTreatments()) {
                        if (t.getTreatmentDetails().contains(treatmentName)) {
                            selectedTreatment = t;
                            break;
                        }
                    }

                    if (selectedTreatment != null) {
                        Patient patient = bookingSystem.getPatientByID(patientID);
                        Appointment appointment = bookingSystem.bookAppointment(patient, physiotherapist, selectedTreatment, time);
                        System.out.println("Appointment booked successfully! " + appointment.getBookingDetails());
                    } else {
                        System.out.println("Treatment not found. Please check the treatment name.");
                    }
                    break;

                case 4:
                    // Change an appointment (only the time)
                    System.out.println("Changing an appointment...");
                    System.out.print("Enter Booking ID to change: ");
                    int oldBookingID = scanner.nextInt();
                    Appointment oldAppointment = bookingSystem.getAppointmentByID(oldBookingID);

                    if (oldAppointment != null) {
                        System.out.print("Enter New Appointment Time (YYYY-MM-DD HH:MM): ");
                        scanner.nextLine();  // Consume newline
                        String newTime = scanner.nextLine();

                        // Ensure the new time is available (add custom logic here if needed)
                        if (isTimeAvailable(newTime)) {
                            bookingSystem.changeAppointment(oldAppointment, newTime);
                            System.out.println("Appointment changed successfully! " + oldAppointment.getBookingDetails());
                        } else {
                            System.out.println("The new time is not available. Please choose a different time.");
                        }
                    } else {
                        System.out.println("Booking ID not found.");
                    }
                    break;

                case 5:
                    // Attend a treatment appointment
                    System.out.println("Marking an appointment as attended...");
                    System.out.print("Enter Booking ID to attend: ");
                    int attendBookingID = scanner.nextInt();
                    Appointment attendAppointment = bookingSystem.getAppointmentByID(attendBookingID);
                    if (attendAppointment != null) {
                        attendAppointment.attend();  // Mark the appointment as attended
                        System.out.println("Appointment attended: " + attendAppointment.getBookingDetails());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;

                case 6:
                    // Print Report
                    System.out.println("Printing the report...");
                    bookingSystem.generateReport();
                    break;

                case 7:
                    // Exit the system
                    System.out.println("Exiting the system...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method to check if the time is available
    public static boolean isTimeAvailable(String time) {
        // Simulate checking time availability here, return true if available
        return true;
    }
}
