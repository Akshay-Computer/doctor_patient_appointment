import java.time.LocalDateTime;

public class Appointment {
    private long id;
    private LocalDateTime dateTime;
    private BookingStatus status;
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Treatment treatment;

    public Appointment(long id, LocalDateTime dateTime, Physiotherapist physio, Treatment treatment) {
        this.id = id;
        this.dateTime = dateTime;
        this.physiotherapist = physio;
        this.treatment = treatment;
        this.status = BookingStatus.BOOKED;
    }

    public long getId() { return id; }
    public BookingStatus getStatus() { return status; }
    public LocalDateTime getDateTime() { return dateTime; }

    public void assignPatient(Patient patient) {
        this.patient = patient;
    }

    public void updateStatus(BookingStatus status) {
        this.status = status;
    }

    public Physiotherapist getPhysiotherapist() { return physiotherapist; }
    public Treatment getTreatment() { return treatment; }
    public Patient getPatient() { return patient; }

    @Override
    public String toString() {
        return "[" + id + "] " + treatment.getName() + " with " + physiotherapist.getFullName() +
                " at " + dateTime + " - Status: " + status;
    }
}
