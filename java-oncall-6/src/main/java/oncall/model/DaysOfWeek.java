package oncall.model;

import java.util.Arrays;

public enum DaysOfWeek {
    SUN("일", 0),
    MON("월", 1),
    TUE("화", 2),
    WED("수", 3),
    THU("목", 4),
    FRI("금", 5),
    SAT("토", 6),
    NONE("해당없음", 7);

    private final String daysOfWeek;
    private final int index;

    DaysOfWeek(String daysOfWeek, int index) {
        this.daysOfWeek = daysOfWeek;
        this.index = index;
    }

    public static DaysOfWeek findDayOfWeekByString(String token) {
        return Arrays.stream(DaysOfWeek.values())
                .filter(daysOfWeek -> daysOfWeek.isSameString(token))
                .findFirst()
                .orElse(NONE);
    }
    private boolean isSameString(String token) {
        return daysOfWeek.equals(token);
    }

    public static DaysOfWeek findDaysOfWeekByIndex(int index) {
        return Arrays.stream(DaysOfWeek.values())
                .filter(daysOfWeek -> daysOfWeek.isSameRemainder(index))
                .findFirst()
                .orElse(NONE);
    }
    private boolean isSameRemainder(int input) {
        return input % 7 == index;
    }

    public static boolean isWeekend(DaysOfWeek daysOfWeek) {
        return daysOfWeek == DaysOfWeek.SAT || daysOfWeek == DaysOfWeek.SUN;
    }
    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public int getIndex() {
        return index;
    }
}
