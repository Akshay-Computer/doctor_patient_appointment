public class ReportGenerator {
    private BookingSystem system;

    public ReportGenerator(BookingSystem system) {
        this.system = system;
    }

    public void generateAppointmentReport() {
        system.generateReport();
    }

    public void generatePhysiotherapistPerformanceReport() {
        // Here you can implement performance tracking logic
    }
}
