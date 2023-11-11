package christmas.model.event;

import static christmas.model.event.EventName.GIVEAWAY;
import static christmas.model.menu.Menu.CHAMPAIGN;

import christmas.model.Discounts;
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
            getDiscountBenefit(countTable);
            getGiveawayBenefit(beforeDiscount, giveaway);
        }
    }
    private void getDiscountBenefit(Map<MenuGroup, Integer> countTable) {
        Discounts.getChristmasDiscount(date, events);
        Discounts.getSpecialDiscount(date, events);
        Discounts.getWeekdayDiscount(date, events, countTable);
        Discounts.getWeekendDiscount(date, events, countTable);
    }
    public void getGiveawayBenefit(int beforeDiscount, Giveaway giveaway) {
        if (beforeDiscount >= 120000) {
            giveaway.addMenu(CHAMPAIGN, 1);
            events.put(GIVEAWAY, -giveaway.getSum());
        }
    }
    public int getTotalBenefit() {
        int totalBenefit = 0;
        for (EventName eventName : events.keySet()) {
            totalBenefit -= events.get(eventName);
        }
        return totalBenefit;
    }
    public int getAfterDiscounts(int beforeDiscount, Giveaway giveaway) {
        return beforeDiscount - getTotalBenefit() + giveaway.getSum();
    }
    public Map<EventName, Integer> getEventTable() {
        return Collections.unmodifiableMap(events);
    }
}
