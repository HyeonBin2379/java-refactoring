package christmas.model;

public enum DaysOfWeek {
    FRI(1),
    SAT(2),
    SUN(3);

    private final int day;

    DaysOfWeek(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
