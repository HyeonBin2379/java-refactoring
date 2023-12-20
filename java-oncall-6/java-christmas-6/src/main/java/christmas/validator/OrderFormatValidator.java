package christmas.validator;

import static christmas.constants.message.ErrorMessage.INVALID_ORDER;
import static christmas.constants.others.MarksAndConstants.EACH_ORDER_TOKEN_COUNT;
import static christmas.constants.others.MarksAndConstants.HYPHEN;

import christmas.model.menu.Menu;
import java.util.Map;

public class OrderFormatValidator {

    public static void validateChar(String input, String mark) {
        int lastIndex = input.length()-1;
        validateFirstChar(input, mark);
        validateContinuousChar(input, mark);
        validateLastChar(input, mark, lastIndex);
    }
    private static void validateFirstChar(String input, String mark) {
        if (input.charAt(0) == mark.charAt(0)) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
    private static void validateLastChar(String input, String mark, int lastIndex) {
        if (input.charAt(lastIndex) == mark.charAt(0)) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
    private static void validateContinuousChar(String input, String mark) {
        if (input.contains(mark + mark)) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    public static String[] validateHyphen(String token) {
        validateHyphenNotContained(token);
        validateChar(token, HYPHEN);
        return token.split(HYPHEN, EACH_ORDER_TOKEN_COUNT);
    }
    private static void validateHyphenNotContained(String token) {
        if (isHyphenNotIncluded(token)) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
    private static boolean isHyphenNotIncluded(String token) {
        return !token.contains(HYPHEN);
    }

    public static Menu validatePossibleMenu(String menuName) {
        Menu foundMenuName = Menu.findMenuName(menuName);
        if (foundMenuName == Menu.NONE) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return foundMenuName;
    }

    public static void validateDuplicatedMenu(String menuName, Map<Menu, Integer> orderTable) {
        if (orderTable.containsKey(Menu.findMenuName(menuName))) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    public static int validateQuantityInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    public static void validatePositive(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
}
