package christmas.model.event;

import static christmas.model.event.EventName.GIVEAWAY;

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

    public void addBenefit(int beforeDiscount, Giveaway giveaway) {
        addEventDiscount(beforeDiscount);
        addEventGiveaway(giveaway);
    }
    private void addEventDiscount(int beforeDiscount) {
        Map<MenuGroup, Integer> countTable = MenuGroup.getCountsByGroup(totalOrder);

        if (beforeDiscount >= 10000) {
            Discounts.getBenefit(date, events, countTable);
        }
    }
    private void addEventGiveaway(Giveaway giveaway) {
        events.put(GIVEAWAY, (-1)*giveaway.getSum());
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
