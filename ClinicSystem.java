import java.util.*;

public class ClinicSystem {
    private List<Physiotherapist> physiotherapists = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    public void addPhysiotherapist(Physiotherapist p) {
        physiotherapists.add(p);
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void removePatient(long patientId) {
        patients.removeIf(p -> p.getId() == patientId);
    }

    public List<Physiotherapist> findByExpertise(String expertise) {
        List<Physiotherapist> result = new ArrayList<>();
        for (Physiotherapist p : physiotherapists) {
            if (p.getAreasOfExpertise().contains(expertise)) {
                result.add(p);
            }
        }
        return result;
    }

    public Physiotherapist findByName(String name) {
        for (Physiotherapist p : physiotherapists) {
            if (p.getFullName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public void bookAppointment(long patientId, long appointmentId) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        for (Physiotherapist p : physiotherapists) {
            for (Appointment a : p.getTimetable().getAppointments()) {
                if (a.getId() == appointmentId && a.getPatient() == null) {
                    a.assignPatient(patient);
                    patient.bookAppointment(a);
                    System.out.println("Appointment booked successfully.");
                    return;
                }
            }
        }

        System.out.println("Appointment not found or already booked.");
    }

    public void cancelAppointment(long appointmentId) {
        for (Physiotherapist p : physiotherapists) {
            for (Appointment a : p.getTimetable().getAppointments()) {
                if (a.getId() == appointmentId && a.getPatient() != null) {
                    Patient patient = a.getPatient();
                    patient.cancelAppointment(a);
                    a.assignPatient(null);
                    a.updateStatus(BookingStatus.CANCELLED);
                    System.out.println("Appointment cancelled.");
                    return;
                }
            }
        }
        System.out.println("Appointment not found or not booked.");
    }

    public void attendAppointment(long appointmentId) {
        for (Physiotherapist p : physiotherapists) {
            for (Appointment a : p.getTimetable().getAppointments()) {
                if (a.getId() == appointmentId && a.getPatient() != null) {
                    a.updateStatus(BookingStatus.ATTENDED);
                    System.out.println("Appointment marked as attended.");
                    return;
                }
            }
        }
        System.out.println("Appointment not found or not booked.");
    }

    private Patient findPatientById(long id) {
        for (Patient p : patients) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }
}
