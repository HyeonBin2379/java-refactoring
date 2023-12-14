package christmas.model.event;

import static christmas.constants.others.MarksAndConstants.DISCOUNT_LOW_LIMIT;
import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;

import christmas.model.menu.MenuGroup;
import christmas.model.order.Order;
import java.util.Map;
import java.util.function.BiConsumer;

public class Discounts {

    private final int date;
    private final Order order;
    private int beforeDiscount;

    public Discounts(int date, Order order) {
        this.date = date;
        this.order = order;
        this.beforeDiscount = order.getTotalCost();
    }

    public void getDiscountBenefit(Map<EventName, Integer> events) {
        Map<MenuGroup, Integer> countTable = MenuGroup.getCountsByGroup(order.getOrder());
        if (beforeDiscount >= DISCOUNT_LOW_LIMIT) {
            addDiscounts(events, countTable);
        }
    }
    public void addDiscounts(Map<EventName, Integer> possible, Map<MenuGroup, Integer> counts) {
        BiConsumer<EventName, Integer> discount = (event, count) -> addPossibleDiscount(possible, event, count);
        discount.accept(CHRISTMAS, date);
        discount.accept(SPECIAL, date);
        discount.accept(WEEKDAY, counts.get(MenuGroup.DESSERT));
        discount.accept(WEEKEND, counts.get(MenuGroup.MAIN_DISH));
    }
    private void addPossibleDiscount(Map<EventName, Integer> possible, EventName event, int count) {
        if (event.canDiscount(date)) {
            int discount = event.getDiscount(date, count);
            if (discount < 0) {
                beforeDiscount += discount;
                possible.put(event, event.getDiscount(date, count));
            }
        }
    }
}