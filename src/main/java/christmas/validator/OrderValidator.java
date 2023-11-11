package christmas.validator;

import static christmas.constants.message.ErrorMessage.ONLY_BEVERAGE;
import static christmas.constants.message.ErrorMessage.TOO_MANY_ORDER;
import static christmas.model.menu.MenuGroup.BEVERAGE;
import static christmas.validator.OrderFormatValidator.validateDuplicatedMenuName;
import static christmas.validator.OrderFormatValidator.validatePositive;
import static christmas.validator.OrderFormatValidator.validatePossibleMenu;
import static christmas.validator.OrderFormatValidator.validateQuantityInteger;

import christmas.model.menu.Menu;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderValidator {

    public static Menu validateMenuName(String menuName, Map<Menu, Integer> orderName) {
        Menu foundMenuName = validatePossibleMenu(menuName);
        validateDuplicatedMenuName(menuName, orderName);
        return foundMenuName;
    }
    public static int validateQuantity(String token) {
        int quantity = validateQuantityInteger(token);
        validatePositive(quantity);
        return quantity;
    }
    public static void validateTotalOrder(int totalCounts, Map<Menu, Integer> orderTable) {
        validateOverOrderLimits(totalCounts);
        validateOnlyBeverage(orderTable);
    }
    private static void validateOverOrderLimits(int totalCounts) {
        if (totalCounts > 20) {
            throw new IllegalArgumentException(TOO_MANY_ORDER.getErrorMsg());
        }
    }
    private static void validateOnlyBeverage(Map<Menu, Integer> orderTable) {
        Set<Menu> beverage = new HashSet<>(BEVERAGE.getList());
        if (beverage.containsAll(orderTable.keySet())) {
            throw new IllegalArgumentException(ONLY_BEVERAGE.getErrorMsg());
        }
    }
}
