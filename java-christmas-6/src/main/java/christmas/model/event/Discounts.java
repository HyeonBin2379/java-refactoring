package christmas.model.event;

import static christmas.constants.others.DaysOfWeek.isFriday;
import static christmas.constants.others.DaysOfWeek.isSaturday;
import static christmas.constants.others.DaysOfWeek.isSunday;
import static christmas.constants.others.MarksAndConstants.DISCOUNT_LOW_LIMIT;
import static christmas.constants.others.MarksAndConstants.X_MAS;
import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;
import static christmas.model.menu.MenuGroup.DESSERT;
import static christmas.model.menu.MenuGroup.MAIN_DISH;

import christmas.model.menu.MenuGroup;
import christmas.model.order.Order;
import java.util.Map;

public class Discounts {

    private final Order order;

    public Discounts(Order order) {
        this.order = order;
    }

    public void getDiscountBenefit(int date, Map<EventName, Integer> events) {
        int beforeDiscounts = order.getTotalCost();
        Map<MenuGroup, Integer> countTable = MenuGroup.getCountsByGroup(order.getOrder());
        if (beforeDiscounts >= DISCOUNT_LOW_LIMIT) {
            getDiscounts(date, events, countTable);
        }
    }

    private void getDiscounts(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        getChristmasDiscount(date, events);
        getSpecialDiscount(date, events);
        getWeekdayDiscount(date, events, counts);
        getWeekendDiscount(date, events, counts);
    }

    public void getChristmasDiscount(int date, Map<EventName, Integer> events) {
        if (date <= X_MAS) {
            events.put(CHRISTMAS, CHRISTMAS.getDiscount(date));
        }
    }

    public void getSpecialDiscount(int date, Map<EventName, Integer> events) {
        if (isSpecialDay(date)) {
            events.put(SPECIAL, SPECIAL.getDiscount(date));
        }
    }
    private boolean isSpecialDay(int date) {
        return isSunday(date) || date == X_MAS;
    }

    public void getWeekdayDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        if (!isWeekend(date)) {
            if (counts.get(DESSERT) > 0) {
                events.put(WEEKDAY, WEEKDAY.getDiscount(counts.get(DESSERT)));
            }
        }
    }

    public void getWeekendDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        if (isWeekend(date)) {
            if (counts.get(MAIN_DISH) > 0) {
                events.put(WEEKEND, WEEKEND.getDiscount(counts.get(MAIN_DISH)));
            }
        }
    }
    private boolean isWeekend(int date) {
        return isFriday(date) || isSaturday(date);
    }
}