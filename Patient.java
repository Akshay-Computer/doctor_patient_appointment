import java.util.*;

public class Patient {
    private long id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private List<Appointment> appointments;

    public Patient(long id, String fullName, String address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.appointments = new ArrayList<>();
    }

    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public List<Appointment> getAppointments() { return appointments; }

    public void bookAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public void cancelAppointment(Appointment appt) {
        appointments.remove(appt);
    }

    @Override
    public String toString() {
        return fullName + " (ID: " + id + ")";
    }
}
