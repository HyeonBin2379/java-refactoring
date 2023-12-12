package christmas.model.event;

import static christmas.constants.others.MarksAndConstants.GIVEAWAY_LOW_LIMIT;

import christmas.model.menu.Menu;
import christmas.model.order.Order;
import java.util.EnumMap;
import java.util.Map;

public class Giveaway {

    private final Order order;
    private final Map<Menu, Integer> giveaway;

    public Giveaway(Order order) {
        this.order = order;
        this.giveaway = new EnumMap<>(Menu.class);
    }

    public void addMenu(String name, int quantity) {
        Menu menuName = Menu.findMenuName(name);
        if (isMatchedCondition(menuName, quantity)) {
            giveaway.put(menuName, quantity);
        }
    }

    private boolean isMatchedCondition(Menu menuName, int quantity) {
        return order.getTotalCost() >= GIVEAWAY_LOW_LIMIT && isValidGiveaway(menuName, quantity);
    }
    private boolean isValidGiveaway(Menu menuName, int quantity) {
        return isValidMenu(menuName) && quantity > 0;
    }
    private boolean isValidMenu(Menu menu) {
        return menu != Menu.NONE;
    }

    public int getSum() {
        return giveaway.keySet()
                .stream()
                .mapToInt(menu -> menu.getPrice() * giveaway.get(menu))
                .sum();
    }

    public Map<Menu, Integer> getGiveaway() {
        return giveaway;
    }
}