import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private Physiotherapist physiotherapist;
    private List<List<Appointment>> weeklySchedule;

    public Timetable(Physiotherapist physiotherapist) {
        this.physiotherapist = physiotherapist;
        this.weeklySchedule = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            weeklySchedule.add(new ArrayList<>());
        }
    }

    public void addAppointment(int week, Appointment appointment) {
        weeklySchedule.get(week).add(appointment);
    }

    public void removeAppointment(int week, Appointment appointment) {
        weeklySchedule.get(week).remove(appointment);
    }

    public List<List<Appointment>> getWeeklySchedule() {
        return weeklySchedule;
    }
}
