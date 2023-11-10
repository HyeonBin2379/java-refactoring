package christmas.model;

import christmas.util.Validation;

public class ReservedDate {
    private final int date;

    public ReservedDate(String input) {
        this.date = validate(input);
    }

    private int validate(String input) {
        int validDate = Validation.validateInteger(input);
        Validation.validateDateRange(validDate);
        return validDate;
    }
    public int getDate() {
        return date;
    }
}
