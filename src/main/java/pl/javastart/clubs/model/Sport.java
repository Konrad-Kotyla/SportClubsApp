package pl.javastart.clubs.model;

public enum Sport {
    BASEBALL("Baseball"),
    HOKEY("Hokej"),
    BASKETBALL("Koszykówka"),
    FOOTBALL("Piłka nożna"),
    HANDBALL("Piłka ręczna"),
    VOLLEYBALL("Siatkówka");

    private final String name;

    Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
