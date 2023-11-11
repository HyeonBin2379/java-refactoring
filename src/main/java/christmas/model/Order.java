package christmas.model;

import static christmas.validator.OrderFormatValidator.validateDash;
import static christmas.validator.OrderValidator.validateMenuName;
import static christmas.validator.OrderValidator.validateQuantity;
import static christmas.validator.OrderValidator.validateTotalOrder;

import christmas.model.menu.Menu;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderTable;

    public Order() {
        this.orderTable = new EnumMap<>(Menu.class);
    }

    public void setOrderTable(List<String> allOrders) {
        for (String order : allOrders) {
            setValidOrderToken(order);
        }
        validateTotalOrder(getTotalCounts(), orderTable);
    }
    private void setValidOrderToken(String order) {
        String[] tokens = validateDash(order);
        Menu menuName = validateMenuName(tokens[0], orderTable);
        int quantity = validateQuantity(tokens[1]);
        orderTable.put(menuName, quantity);
    }

    public int getTotalCounts() {
        int totalCounts = 0;
        for (Menu menuName : orderTable.keySet()) {
            totalCounts += orderTable.get(menuName);
        }
        return totalCounts;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Menu menuName : orderTable.keySet()) {
            totalCost += menuName.getPrice() * orderTable.get(menuName);
        }
        return totalCost;
    }

    public Map<Menu, Integer> getOrder() {
        return Collections.unmodifiableMap(orderTable);
    }

    public void clearOrder() {
        orderTable.clear();
    }
}
