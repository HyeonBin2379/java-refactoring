package oncall.model;

import java.util.Arrays;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31),
    NONE(0, 0);


    private final int month;
    private final int daysCount;

    Month(int month, int daysCount) {
        this.month = month;
        this.daysCount = daysCount;
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
}
