import java.util.*;

public class Physiotherapist {
    private long id;
    private String fullName;
    private String address;
    private String phoneNumber;
    private List<String> areasOfExpertise;
    private TreatmentTimetable timetable;

    public Physiotherapist(long id, String fullName, List<String> expertise) {
        this.id = id;
        this.fullName = fullName;
        this.areasOfExpertise = expertise;
        this.timetable = new TreatmentTimetable();
    }

    public long getId() { return id; }
    public String getFullName() { return fullName; }
    public TreatmentTimetable getTimetable() { return timetable; }

    public List<String> getAreasOfExpertise() {
        return areasOfExpertise;
    }

    public int getAttendedAppointmentCount() {
        int count = 0;
        for (Appointment appt : timetable.getAppointments()) {
            if (appt.getStatus() == BookingStatus.ATTENDED) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return fullName + " (Expertise: " + areasOfExpertise + ")";
    }
}
