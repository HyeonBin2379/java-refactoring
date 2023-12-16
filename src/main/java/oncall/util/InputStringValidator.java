package oncall.util;

public class InputStringValidator {
    public static String[] validateInputString(String input) {
        validateBlankOrSpace(input);
        validateComma(input);
        return input.split(",");
    }
    public static void validateBlankOrSpace(String input) {
        validateBlank(input);
        validateSpace(input);
    }
    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
    private static void validateSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateComma(String input) {
        validateCommaFirst(input);
        validateCommaSequential(input);
        validateCommaLast(input);
    }
    private static void validateCommaFirst(String input) {
        if (input.charAt(0) == ',') {
            throw new IllegalArgumentException();
        }
    }
    private static void validateCommaSequential(String input) {
        if (input.contains(",,")) {
            throw new IllegalArgumentException();
        }
    }
    private static void validateCommaLast(String input) {
        if (input.charAt(input.length()-1) == ',') {
            throw new IllegalArgumentException();
        }
    }
}
