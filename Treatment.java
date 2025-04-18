public class Treatment {
    private String name;
    private String description;
    private String expertiseArea;

    public Treatment(String name, String description, String expertiseArea) {
        this.name = name;
        this.description = description;
        this.expertiseArea = expertiseArea;
    }

    public String getName() { return name; }
    public String getExpertiseArea() { return expertiseArea; }

    @Override
    public String toString() {
        return name + " (" + expertiseArea + ")";
    }
}
