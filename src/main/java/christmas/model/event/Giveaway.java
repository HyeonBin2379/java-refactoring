package christmas.model.event;

import christmas.model.menu.Menu;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Giveaway {

    private final int beforeDiscount;
    private final Map<Menu, Integer> giveaway;

    public Giveaway(int beforeDiscount) {
        this.beforeDiscount = beforeDiscount;
        this.giveaway = new EnumMap<>(Menu.class);
    }

    public void addMenu(String name, int quantity) {
        Menu menuName = Menu.findMenuName(name);
        if (isMatchedCondition(menuName, quantity)) {
            giveaway.put(menuName, quantity);
        }
    }
    private boolean isMatchedCondition(Menu menuName, int quantity) {
        return beforeDiscount >= 120000 && menuName != Menu.NONE && quantity > 0;
    }

    public int getSum() {
        int totalGiveAway = 0;
        for (Menu menu : giveaway.keySet()) {
            totalGiveAway += menu.getPrice()*giveaway.get(menu);
        }
        return totalGiveAway;
    }

    public Map<Menu, Integer> getGiveaway() {
        return Collections.unmodifiableMap(giveaway);
    }
}