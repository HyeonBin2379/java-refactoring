package christmas.model.event;

import static christmas.model.Discounts.getChristmasDiscount;
import static christmas.model.Discounts.getGiveaway;
import static christmas.model.Discounts.getSpecialDiscount;
import static christmas.model.Discounts.getWeekdayDiscount;
import static christmas.model.Discounts.getWeekendDiscount;

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
            getChristmasDiscount(date, events);
            getWeekdayDiscount(date, events, countTable);
            getWeekendDiscount(date, events, countTable);
            getSpecialDiscount(date, events);
            getGiveaway(events, beforeDiscount, giveaway);
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
