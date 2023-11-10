package christmas.model;

import christmas.util.DateValidator;

public class ReservedDate {
    private final int date;

    public ReservedDate(String input) {
        this.date = validate(input);
    }

    private int validate(String input) {
        int validDate = DateValidator.validateDateInteger(input);
        DateValidator.validateDateRange(validDate);
        return validDate;
    }
    public int getDate() {
        return date;
    }
}
