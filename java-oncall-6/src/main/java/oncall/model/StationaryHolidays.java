package oncall.model;

import static oncall.model.Month.DECEMBER;
import static oncall.model.Month.JANUARY;
import static oncall.model.Month.JULY;
import static oncall.model.Month.JUNE;
import static oncall.model.Month.MARCH;
import static oncall.model.Month.MAY;
import static oncall.model.Month.NONE;
import static oncall.model.Month.OCTOBER;

import java.util.Arrays;

public enum StationaryHolidays {
    NEW_YEAR(JANUARY, 1),
    SAMILJEOL(MARCH, 1),
    CHILDREN_DAY(MAY, 5),
    MEMORIAL_DAY(JUNE, 6),
    INDEPENDENCE_DAY(JULY, 15),
    GAECHEONJEOL(OCTOBER, 3),
    HANGEULNAL(OCTOBER, 9),
    CHRISTMAS(DECEMBER, 25),
    NONE(Month.NONE, 0);

    private final Month month;
    private final int day;

    StationaryHolidays(Month month, int day) {
        this.month = month;
        this.day = day;
    }

    public static StationaryHolidays findHoliday(Month month, int day) {
        return Arrays.stream(StationaryHolidays.values())
                .filter(stationaryHoliday -> stationaryHoliday.isStationaryHoliday(month, day))
                .findFirst()
                .orElse(NONE);
    }
    private boolean isStationaryHoliday(Month month, int day) {
        return this.month == month && this.day == day;
    }
    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
