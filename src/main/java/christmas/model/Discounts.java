package christmas.model;

import static christmas.constants.others.Calender.X_MAS;
import static christmas.constants.others.Calender.FRI;
import static christmas.constants.others.Calender.ONE_WEEK;
import static christmas.constants.others.Calender.SAT;
import static christmas.constants.others.Calender.SUN;
import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;
import static christmas.model.menu.MenuGroup.DESSERT;
import static christmas.model.menu.MenuGroup.MAIN_DISH;

import christmas.model.event.EventName;
import christmas.model.menu.MenuGroup;
import java.util.Map;

public class Discounts {

    public static void getChristmasDiscount(int date, Map<EventName, Integer> events) {
        if (date <= X_MAS.getDay()) {
            int discount = -(900 + 100*date);
            events.put(CHRISTMAS, discount);
        }
    }
    public static void getSpecialDiscount(int date, Map<EventName, Integer> events) {
        if (date % ONE_WEEK.getDay() == SUN.getDay() || date == X_MAS.getDay()) {
            int discount = -1000;
            events.put(SPECIAL, discount);
        }
    }
    public static void getWeekdayDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        if (date % ONE_WEEK.getDay() != FRI.getDay() && date % ONE_WEEK.getDay() != SAT.getDay()) {
            int discount = 2023 * counts.get(DESSERT)*(-1);
            events.put(WEEKDAY, discount);
        }
    }
    public static void getWeekendDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        if (date % ONE_WEEK.getDay() == FRI.getDay() || date % ONE_WEEK.getDay() == SAT.getDay()) {
            int discount = 2023 * counts.get(MAIN_DISH)*(-1);
            events.put(WEEKEND, discount);
        }
    }
}