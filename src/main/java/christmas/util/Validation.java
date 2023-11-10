package christmas.util;

import static christmas.constants.menu.MenuGroup.BEVERAGE;

import christmas.constants.menu.Menu;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        int lastIndex = input.length() - 1;
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

    public static String[] validateDash(String token) {
        if (isNotIncluded(token, "-")) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }
        return foundMenuName;
    }

    private static void validateDuplicatedMenuName(String menuName, Map<Menu, Integer> orderTable) {
        if (orderTable.containsKey(Menu.valueOf(menuName))) {
            throw new IllegalArgumentException();
        }
    }

    public static int validateQuantity(String token) {
        int quantity = validateInteger(token);
        validatePositive(quantity);
        return quantity;
    }

    private static void validatePositive(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateTotalOrder(int totalCounts, Map<Menu, Integer> orderTable) {
        validateOverOrderLimits(totalCounts);
        validateOnlyBeverage(orderTable);
    }

    private static void validateOverOrderLimits(int totalCounts) {
        if (totalCounts > 20) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateOnlyBeverage(Map<Menu, Integer> orderTable) {
        Set<Menu> beverage = new HashSet<>(BEVERAGE.getList());
        if (beverage.containsAll(orderTable.keySet())) {
            throw new IllegalArgumentException();
        }
    }
}