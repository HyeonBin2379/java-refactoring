package christmas.model;

import static christmas.validator.DateValidator.validateDateInteger;
import static christmas.validator.DateValidator.validateDateRange;

public class OrderDate {
    private final int date;

    public OrderDate(String input) {
        this.date = validate(input);
    }

    private int validate(String input) {
        int validDate = validateDateInteger(input);
        validateDateRange(validDate);
        return validDate;
    }
    public int getDate() {
        return date;
    }
}
