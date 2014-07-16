package by.bsu.airline.sax;

public enum PlaneEnum {
    PLANES("planes"),
    ID("ID"),
    VIEW("view"),
    AIRCOMPANY("aircompany"),
    NAME("name"),
    YEAR("year"),
    COUNTRY("country"),
    CHARACTERISTICS("characteristrics"),
    CAPASITY("capasity"),
    PASSANGER("passanger"),
    FUELWEIGHT("fuel-weight"),
    CRUISINGSPEED("cruising-speed"),
    RANGE("range"),
    PRACTICALCEILING("practical-ceiling"),
    LENGHT("length"),
    HEIGHT("height"),
    WIDTH("width");
    private String value;
    private PlaneEnum(String value) {
      this.value = value;
    }
    public String getValue() {
     return value;
    }
}