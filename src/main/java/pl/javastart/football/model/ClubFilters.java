package pl.javastart.football.model;

public class ClubFilters {
    private String name = "";
    private String country = "";
    private int foundationYear = 0;
    private String footballer = "";

    public ClubFilters() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getFootballer() {
        return footballer;
    }

    public void setFootballer(String footballer) {
        this.footballer = footballer;
    }
}
