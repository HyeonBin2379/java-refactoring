package christmas.model;

import static christmas.model.DaysOfWeek.FRI;
import static christmas.model.DaysOfWeek.SAT;
import static christmas.model.DaysOfWeek.SUN;
import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.GIVEAWAY;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;
import static christmas.model.menu.Menu.CHAMPAIGN;
import static christmas.model.menu.MenuGroup.DESSERT;
import static christmas.model.menu.MenuGroup.MAIN_DISH;

import christmas.model.event.EventName;
import christmas.model.menu.MenuGroup;
import java.util.Map;

public class Discounts {
    public static void getChristmasDiscount(int date, Map<EventName, Integer> events) {
        if (1 <= date && date <= 25) {
            int discount = -(900 + 100 * date);
            events.put(CHRISTMAS, discount);
        }
    }
    public static void getSpecialDiscount(int date, Map<EventName, Integer> events) {
        if (date % 7 == SUN.getDay() || date == 25) {
            events.put(SPECIAL, -1000);
        }
    }
    public static void getWeekdayDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> countTable) {
        if (date % 7 != FRI.getDay() && date % 7 != SAT.getDay()) {
            int discount = 2023*countTable.get(DESSERT)*(-1);
            events.put(WEEKDAY, discount);
        }
    }
    public static void getWeekendDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> countTable) {
        if (date % 7 == FRI.getDay() || date % 7 == SAT.getDay()) {
            int discount = 2023*countTable.get(MAIN_DISH)*(-1);
            events.put(WEEKEND, discount);
        }
    }
    public static void getGiveaway(Map<EventName, Integer> events, int beforeDiscount, Giveaway giveaway) {
        if (beforeDiscount >= 120000) {
            giveaway.addMenu(CHAMPAIGN, 1);
            events.put(GIVEAWAY, -giveaway.getTotalSum());
        }
    }
}
