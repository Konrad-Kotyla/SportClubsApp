package pl.javastart.football.model;

public enum Sport {
    FOOTBALL("Piłka nożna"),
    VOLLEYBALL("Siatkówka"),
    BASKETBALL("Koszykówka"),
    HOKEY("Hokej"),
    BASEBALL("Baseball");

    private final String name;

    Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
