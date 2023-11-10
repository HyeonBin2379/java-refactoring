package christmas.model;

public enum Days {
    FRI(1),
    SAT(2),
    SUN(3);

    private final int day;

    Days(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
