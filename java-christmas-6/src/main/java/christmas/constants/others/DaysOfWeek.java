package christmas.constants.others;

import static christmas.constants.others.MarksAndConstants.ONE_WEEK;

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

    public static boolean isFriday(int date) {
        return date % ONE_WEEK == FRI.getDay();
    }
    public static boolean isSaturday(int date) {
        return date % ONE_WEEK == SAT.getDay();
    }
    public static boolean isSunday(int date) {
        return date % ONE_WEEK == SUN.getDay();
    }
}
