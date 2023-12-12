package lotto.util;

import static lotto.constants.ErrorMessage.DUPLICATED_NUMBER;
import static lotto.constants.ErrorMessage.ERROR_FORMAT;
import static lotto.constants.ErrorMessage.INDIVISIBLE_TO_THOUSAND;
import static lotto.constants.ErrorMessage.INVALID_NUMBER;
import static lotto.constants.ErrorMessage.NOT_INTEGER;
import static lotto.constants.ErrorMessage.NOT_POSITIVE;
import static lotto.constants.ErrorMessage.NOT_SIX_NUMBERS;
import static lotto.constants.MarksAndConstants.MAX_LOTTO_NUM;
import static lotto.constants.MarksAndConstants.MIN_LOTTO_NUM;
import static lotto.constants.MarksAndConstants.NEEDED_LOTTO_NUM;
import static lotto.constants.MarksAndConstants.SINGLE_LOTTO_PRICE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validation {
    public static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + NOT_INTEGER + input));
        }
    }

    public static void validatePositiveNumber(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + NOT_POSITIVE + input));
        }
    }

    public static void validateDivisibleByLottoPrice(int input) {
        if (input % SINGLE_LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + INDIVISIBLE_TO_THOUSAND + input));
        }
    }

    public static void validateNumberRange(int input) {
        if (input < MIN_LOTTO_NUM || input > MAX_LOTTO_NUM) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + INVALID_NUMBER + input));
        }
    }

    public static void validateSixLottoNumbers(List<Integer> numbers) {
        if (numbers.size() != NEEDED_LOTTO_NUM) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + NOT_SIX_NUMBERS));
        }
    }

    public static void validateDuplicatedSixNumber(List<Integer> numbers) {
        Set<Integer> temp = new HashSet<>(numbers);
        if (temp.size() != numbers.size()) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + DUPLICATED_NUMBER));
        }
    }

    public static void validateBonusNumDuplicated(int input, List<Integer> numbers) {
        if (numbers.contains(input)) {
            throw new IllegalArgumentException(String.format(ERROR_FORMAT + DUPLICATED_NUMBER));
        }
    }
}