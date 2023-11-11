package christmas.validator;

import static christmas.constants.message.ErrorMessage.INVALID_ORDER;

import christmas.model.menu.Menu;
import java.util.Map;

public class OrderFormatValidator {
    public static String[] validateDash(String token) {
        if (isNotIncluded(token)) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return token.split("-", 2);
    }
    public static boolean isNotIncluded(String token) {
        return !token.contains("-");
    }

    public static Menu validatePossibleMenu(String menuName) {
        Menu foundMenuName = Menu.findMenuName(menuName);
        if (foundMenuName == Menu.NONE) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return foundMenuName;
    }
    public static void validateDuplicatedMenuName(String menuName, Map<Menu, Integer> orderTable) {
        if (orderTable.containsKey(Menu.findMenuName(menuName))) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
    public static int validateQuantityInteger(String input) {
        int valid;
        try {
            valid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return valid;
    }
    public static void validatePositive(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
}
