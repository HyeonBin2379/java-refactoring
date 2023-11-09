package christmas.util;

public class Validation {
    public static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (input.contains(" ")) {
            throw new IllegalArgumentException();
        }
    }
    public static void validateComma(String input) {
        validateFirstOrLastComma(input);
        validateContinuousComma(input);
    }
    private static void validateFirstOrLastComma(String input) {
        int lastIndex = input.length()-1;
        if (input.charAt(0) == ',' || input.charAt(lastIndex) == ',') {
            throw new IllegalArgumentException();
        }
    }
    private static void validateContinuousComma(String input) {
        if (input.contains(",,")) {
            throw new IllegalArgumentException();
        }
    }

    public static int validateInteger(String input) {
        int valid;
        try {
            valid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return valid;
    }
    public static void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }
}
