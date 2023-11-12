package christmas.model.event;

import christmas.model.menu.Menu;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Giveaway {

    private final Map<Menu, Integer> giveaway;

    public Giveaway() {
        this.giveaway = new EnumMap<>(Menu.class);
    }

    public void addMenu(Menu menuName, int quantity) {
        giveaway.put(menuName, quantity);
    }
    public Map<Menu, Integer> getTable() {
        return Collections.unmodifiableMap(giveaway);
    }
    public int getSum() {
        int totalGiveAway = 0;
        for (Menu menu : giveaway.keySet()) {
            totalGiveAway += menu.getPrice()*giveaway.get(menu);
        }
        return totalGiveAway;
    }
}