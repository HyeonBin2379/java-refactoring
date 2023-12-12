package lotto.util;

import static lotto.constants.ErrorMessage.BLANK_INPUT;
import static lotto.constants.ErrorMessage.ERROR_FORMAT;
import static lotto.constants.ErrorMessage.INVALID_COMMA;
import static lotto.constants.ErrorMessage.SPACE_INCLUDED;
import static lotto.constants.MarksAndConstants.COMMA;
import static lotto.constants.MarksAndConstants.SPACE;

public class InputValidation {
    public static void validateInput(String input) {
        validateBlankInput(input);
        validateSpaceChar(input);
    }

    public static void validateInputWinnerNum(String input) {
        validateInput(input);
        validateComma(input);
    }
    private static void validateBlankInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + BLANK_INPUT));
        }
    }

    private static void validateSpaceChar(String input) {
        if (input.contains(SPACE)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + SPACE_INCLUDED));
        }
    }

    public static void validateComma(String input) {
        validateNotIncludedComma(input);
        validateFirstOrLastComma(input);
        validateContinuousComma(input);
    }

    private static void validateNotIncludedComma(String input) {
        if (!input.contains(COMMA)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + INVALID_COMMA + input));
        }
    }

    private static void validateFirstOrLastComma(String input) {
        int lastIndex = input.length() - 1;
        if (input.charAt(0) == COMMA.charAt(0) || input.charAt(lastIndex) == COMMA.charAt(0)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + INVALID_COMMA + input));
        }
    }

    private static void validateContinuousComma(String input) {
        if (input.contains(COMMA + COMMA)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + INVALID_COMMA + input));
        }
    }
}
