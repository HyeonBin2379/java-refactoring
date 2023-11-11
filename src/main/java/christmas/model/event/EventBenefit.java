package christmas.model.event;

import static christmas.model.Days.FRI;
import static christmas.model.Days.SAT;
import static christmas.model.Days.SUN;
import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.GIVEAWAY;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;
import static christmas.model.menu.Menu.CHAMPAIGN;
import static christmas.model.menu.MenuGroup.DESSERT;
import static christmas.model.menu.MenuGroup.MAIN_DISH;

import christmas.model.Giveaway;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuGroup;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class EventBenefit {

    private final int date;
    private final Map<EventName, Integer> events;
    private final Map<Menu, Integer> totalOrder;

    public EventBenefit(int date, Map<Menu, Integer> totalOrder) {
        this.date = date;
        this.events = new EnumMap<>(EventName.class);
        this.totalOrder = totalOrder;
    }

    public void getEventDiscount(int beforeDiscount, Giveaway giveaway) {
        Map<MenuGroup, Integer> countTable = MenuGroup.getCountsByGroup(totalOrder);
        if (beforeDiscount >= 10000) {
            getChristmasDiscount();
            getWeekdayDiscount(countTable);
            getWeekendDiscount(countTable);
            getSpecialDiscount();
            getGiveaway(beforeDiscount, giveaway);
        }
    }
    private void getChristmasDiscount() {
        if (1 <= date && date <= 25) {
            int discount = -(900 + 100 * date);
            events.put(CHRISTMAS, discount);
        }
    }
    private void getSpecialDiscount() {
        if (date % 7 == SUN.getDay() || date == 25) {
            events.put(SPECIAL, -1000);
        }
    }
    private void getWeekdayDiscount(Map<MenuGroup, Integer> countTable) {
        if (date % 7 != FRI.getDay() && date % 7 != SAT.getDay()) {
            int discount = 2023*countTable.get(DESSERT)*(-1);
            events.put(WEEKDAY, discount);
        }
    }
    private void getWeekendDiscount(Map<MenuGroup, Integer> countTable) {
        if (date % 7 == FRI.getDay() || date % 7 == SAT.getDay()) {
            int discount = 2023*countTable.get(MAIN_DISH)*(-1);
            events.put(WEEKEND, discount);
        }
    }
    private void getGiveaway(int beforeDiscount, Giveaway giveaway) {
        if (beforeDiscount >= 120000) {
            giveaway.addMenu(CHAMPAIGN, 1);
            events.put(GIVEAWAY, -giveaway.getTotalSum());
        }
    }

    public int getTotalBenefit() {
        int totalBenefit = 0;
        for (EventName eventName : events.keySet()) {
            totalBenefit -= events.get(eventName);
        }
        return totalBenefit;
    }
    public Map<EventName, Integer> getEventTable() {
        return Collections.unmodifiableMap(events);
    }
}
