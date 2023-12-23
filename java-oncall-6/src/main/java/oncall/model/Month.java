package oncall.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Month {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, Collections.EMPTY_LIST),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, Collections.EMPTY_LIST),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, Collections.EMPTY_LIST),
    JULY(7, 31, Collections.EMPTY_LIST),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, Collections.EMPTY_LIST),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, Collections.EMPTY_LIST),
    DECEMBER(12, 31, List.of(25)),
    NONE(0, 0, Collections.EMPTY_LIST);

    private final int month;
    private final int daysCount;
    private final List<Integer> stationaryHolidays;

    Month(int month, int daysCount, List<Integer> stationaryHolidays) {
        this.month = month;
        this.daysCount = daysCount;
        this.stationaryHolidays = stationaryHolidays;
    }

    public static Month findMonth(int month) {
        return Arrays.stream(Month.values())
                .filter(month1 -> month1.month == month)
                .findFirst()
                .orElse(NONE);
    }
    public int getMonth() {
        return month;
    }
    public int getDaysCount() {
        return daysCount;
    }
    public static boolean isStationaryHoliday(Month month, int day) {
        return month.isTodayStationaryHoliday(day);
    }
    private boolean isTodayStationaryHoliday(int today) {
        return stationaryHolidays.contains(today);
    }
}
