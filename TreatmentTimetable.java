import java.util.*;

public class TreatmentTimetable {
    private List<Appointment> appointments;

    public TreatmentTimetable() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAvailableAppointments() {
        List<Appointment> available = new ArrayList<>();
        for (Appointment appt : appointments) {
            if (appt.getStatus() == BookingStatus.BOOKED && appt.getPatient() == null) {
                available.add(appt);
            }
        }
        return available;
    }
}
