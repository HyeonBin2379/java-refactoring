package christmas.model;

import christmas.util.DateValidator;

public class OrderDate {
    private final int date;

    public OrderDate(String input) {
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
