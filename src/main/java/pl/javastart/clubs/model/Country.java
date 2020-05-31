package pl.javastart.clubs.model;

public enum Country {
    ARGENTINA("Argentyna"),
    AUSTRIA("Austria"),
    BELGIUM("Belgia"),
    BOSNIA_AND_HERZEGOVINA("Bośnia i Hercegowina"),
    BRAZIL("Brazylia"),
    BULGARIA("Bułgaria"),
    CHILE("Chile"),
    CHINA("Chiny"),
    CROATIA("Chorwacja"),
    CZECH_REPUBLIC("Czechy"),
    DENMARK("Dania"),
    FRANCE("Francja"),
    GREECE("Grecja"),
    SPAIN("Hiszpania"),
    NETHERLANDS("Holandia"),
    IRELAND("Irlandia"),
    ICELAND("Islandia"),
    JAPAN("Japonia"),
    CAMEROON("Kamerun"),
    MEXICO("Meksyk"),
    GERMANY("Niemcy"),
    NIGERIA("Nigeria"),
    POLAND("Polska"),
    PORTUGAL("Portugalia"),
    RUSSIA("Rosja"),
    SERBIA("Serbia"),
    SLOVAKIA("Słowacja"),
    SWITZERLAND("Szwajcaria"),
    SWEDEN("Szwecja"),
    TURKEY("Turcja"),
    GREAT_BRITAIN("Wielka Brytania"),
    ITALY("Włochy"),
    IVORY_COAST("Wybrzeże Kości Słoniowej"),
    USA("USA");

    private final String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
