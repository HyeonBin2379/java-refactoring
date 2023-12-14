package christmas.validator;

import static christmas.constants.message.ErrorMessage.BLANK_INPUT;
import static christmas.constants.others.MarksAndConstants.COMMA;
import static christmas.constants.others.MarksAndConstants.SPACE;
import static christmas.validator.OrderFormatValidator.validateChar;

public class InputStringValidator {

    public static void validateDateString(String input) {
        validateBlankOrSpace(input);
    }

    public static void validateOrderString(String input) {
        validateBlankOrSpace(input);
        validateChar(input, COMMA);
    }

    public static void validateBlankOrSpace(String input) {
        validateBlank(input);
        validateSpace(input);
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT.getErrorMsg());
        }
    }

    private static void validateSpace(String input) {
        if (input.contains(SPACE)) {
            throw new IllegalArgumentException(BLANK_INPUT.getErrorMsg());
        }
    }
}
