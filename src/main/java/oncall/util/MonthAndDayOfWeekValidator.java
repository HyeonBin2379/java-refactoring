package oncall.util;

import oncall.model.DaysOfWeek;
import oncall.model.Month;

public class MonthAndDayOfWeekValidator {
    public static Month validateMonth(String input) {
        int month = validateInteger(input);
        return validateValidMonth(month);
    }
    private static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
    private static Month validateValidMonth(int month) {
        Month validMonth = Month.findMonth(month);
        if (validMonth == Month.NONE) {
            throw new IllegalArgumentException();
        }
        return validMonth;
    }

    public static DaysOfWeek validateDayOfWeek(String token) {
        DaysOfWeek validDaysOrWeek = DaysOfWeek.findDayOfWeekByString(token);
        if (validDaysOrWeek == DaysOfWeek.NONE) {
            throw new IllegalArgumentException();
        }
        return validDaysOrWeek;
    }
}
