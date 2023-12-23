package oncall.model;

import java.util.Arrays;

public enum DaysOfWeek {
    SUN("일", 0, true),
    MON("월", 1, false),
    TUE("화", 2, false),
    WED("수", 3, false),
    THU("목", 4, false),
    FRI("금", 5, false),
    SAT("토", 6, true),
    NONE("해당없음", 7, false);

    private final String daysOfWeek;
    private final int index;
    private final boolean weekend;

    DaysOfWeek(String daysOfWeek, int index, boolean weekend) {
        this.daysOfWeek = daysOfWeek;
        this.index = index;
        this.weekend = weekend;
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

    public boolean isWeekend() {
        return weekend;
    }
    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public int getIndex() {
        return index;
    }
}
