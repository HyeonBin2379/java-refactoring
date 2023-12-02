package baseball.util;

import static baseball.Constants.ErrorMessage.BLANK_INPUT;
import static baseball.Constants.ErrorMessage.DUPLICATED_NUMBER;
import static baseball.Constants.ErrorMessage.INVALID_MENU;
import static baseball.Constants.ErrorMessage.NOT_INTEGER;
import static baseball.Constants.ErrorMessage.NOT_THREE_DIGIT;
import static baseball.Constants.ErrorMessage.SPACE_INCLUDED;
import static baseball.Constants.ErrorMessage.ZERO_INCLUDED;
import static baseball.Constants.MenuNumber.NONE;

import baseball.Constants.MenuNumber;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validation {

    public static void validateBlankOrSpace(String input) {
        validateBlank(input);
        validateSpace(input);
    }
    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT.getMessage());
        }
    }
    private static void validateSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(SPACE_INCLUDED.getMessage() + input);
        }
    }

    public static void validateInteger(String input) {
        try {
            int temp = Integer.parseInt(input); // 입력값에 문자가 섞이면 Exception 발생
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getMessage() + input);
        }
    }

    public static void validateUserNumber(String input) {
        validateUserNumberLength(input);    // 문자열 길이 검사
        validateNonZero(input);   // 0 포함 여부 검사
    }

    private static void validateUserNumberLength(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException(NOT_THREE_DIGIT.getMessage() + input);
        }
    }

    private static void validateNonZero(String input) {
        if (input.contains("0")) {
            throw new IllegalArgumentException(ZERO_INCLUDED.getMessage() + input);
        }
    }

    public static void validateDuplicatedNumber(List<Integer> user) {
        Set<Integer> temp = new HashSet<>(user);
        if (temp.size() != user.size()) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    public static int validateMenuNumber(String input) {
        validateInteger(input);
        return validateExistMenu(input);
    }
    private static int validateExistMenu(String input) {
        int menuNum = Integer.parseInt(input);
        if (MenuNumber.findMenuNumber(menuNum) == NONE) {
            throw new IllegalArgumentException(INVALID_MENU.getMessage() + menuNum);
        }
        return menuNum;
    }
}
