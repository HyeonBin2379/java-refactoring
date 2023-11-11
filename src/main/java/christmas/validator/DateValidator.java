package christmas.validator;

import static christmas.constants.message.ErrorMessage.INVALID_DATE;

public class DateValidator {
    public static int validateDateInteger(String input) {
        int valid;
        try {
            valid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE.getErrorMsg());
        }
        return valid;
    }

    public static void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE.getErrorMsg());
        }
    }
}