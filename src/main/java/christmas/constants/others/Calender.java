package christmas.constants.others;

public enum Calender {
    FRI(1),
    SAT(2),
    SUN(3),
    ONE_WEEK(7),
    X_MAS(25);

    private final int day;

    Calender(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
