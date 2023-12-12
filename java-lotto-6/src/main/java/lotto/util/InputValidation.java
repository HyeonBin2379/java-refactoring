package lotto.util;

import static lotto.constants.ErrorMessage.BLANK_INPUT;
import static lotto.constants.ErrorMessage.ERROR_FORMAT;
import static lotto.constants.ErrorMessage.SPACE_INCLUDED;
import static lotto.constants.MarksAndConstants.SPACE;
import static lotto.util.Validation.validateComma;

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
}
