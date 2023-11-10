package christmas.util;

import static christmas.constants.message.ErrorMessage.INVALID_ORDER;
import static christmas.model.menu.MenuGroup.BEVERAGE;

import christmas.model.menu.Menu;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderValidator {
    public static void validateComma(String input) {
        validateFirstOrLastComma(input);
        validateContinuousComma(input);
    }

    private static void validateFirstOrLastComma(String input) {
        int lastIndex = input.length() - 1;
        if (input.charAt(0) == ',' || input.charAt(lastIndex) == ',') {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    private static void validateContinuousComma(String input) {
        if (input.contains(",,")) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
    public static String[] validateDash(String token) {
        if (isNotIncluded(token, "-")) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return token.split("-", 2);
    }

    private static boolean isNotIncluded(String token, String str) {
        return !token.contains(str);
    }

    public static Menu validateMenuName(String menuName, Map<Menu, Integer> orderName) {
        Menu foundMenuName = validatePossibleMenu(menuName);
        validateDuplicatedMenuName(menuName, orderName);
        return foundMenuName;
    }

    private static Menu validatePossibleMenu(String menuName) {
        Menu foundMenuName = Menu.findMenuName(menuName);
        if (foundMenuName == Menu.NONE) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return foundMenuName;
    }

    private static void validateDuplicatedMenuName(String menuName, Map<Menu, Integer> orderTable) {
        if (orderTable.containsKey(Menu.findMenuName(menuName))) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    public static int validateQuantity(String token) {
        int quantity = validateQuantityInteger(token);
        validatePositive(quantity);
        return quantity;
    }
    private static int validateQuantityInteger(String input) {
        int valid;
        try {
            valid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
        return valid;
    }
    private static void validatePositive(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    public static void validateTotalOrder(int totalCounts, Map<Menu, Integer> orderTable) {
        validateOverOrderLimits(totalCounts);
        validateOnlyBeverage(orderTable);
    }

    private static void validateOverOrderLimits(int totalCounts) {
        if (totalCounts > 20) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }

    private static void validateOnlyBeverage(Map<Menu, Integer> orderTable) {
        Set<Menu> beverage = new HashSet<>(BEVERAGE.getList());
        if (beverage.containsAll(orderTable.keySet())) {
            throw new IllegalArgumentException(INVALID_ORDER.getErrorMsg());
        }
    }
}
