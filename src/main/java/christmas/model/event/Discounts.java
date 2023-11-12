package christmas.model.event;

import static christmas.constants.others.Calender.FRI;
import static christmas.constants.others.Calender.ONE_WEEK;
import static christmas.constants.others.Calender.SAT;
import static christmas.constants.others.Calender.SUN;
import static christmas.constants.others.Calender.X_MAS;
import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;
import static christmas.model.menu.MenuGroup.DESSERT;
import static christmas.model.menu.MenuGroup.MAIN_DISH;

import christmas.model.menu.MenuGroup;
import java.util.Map;

public class Discounts {

    public static void getBenefit(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> countTable) {
        getChristmasDiscount(date, events);
        getSpecialDiscount(date, events);
        getWeekdayDiscount(date, events, countTable);
        getWeekendDiscount(date, events, countTable);
    }
    public static void getChristmasDiscount(int date, Map<EventName, Integer> events) {
        if (date <= X_MAS.getDay()) {
            events.put(CHRISTMAS, CHRISTMAS.getDiscount(date));
        }
    }
    public static void getSpecialDiscount(int date, Map<EventName, Integer> events) {
        if (date % ONE_WEEK.getDay() == SUN.getDay() || date == X_MAS.getDay()) {
            events.put(SPECIAL, SPECIAL.getDiscount(date));
        }
    }
    public static void getWeekdayDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        if (date % ONE_WEEK.getDay() != FRI.getDay() && date % ONE_WEEK.getDay() != SAT.getDay()) {
            if (counts.get(DESSERT) > 0) {
                events.put(WEEKDAY, WEEKDAY.getDiscount(counts.get(DESSERT)));
            }
        }
    }
    public static void getWeekendDiscount(int date, Map<EventName, Integer> events, Map<MenuGroup, Integer> counts) {
        if (date % ONE_WEEK.getDay() == FRI.getDay() || date % ONE_WEEK.getDay() == SAT.getDay()) {
            if (counts.get(MAIN_DISH) > 0) {
                events.put(WEEKEND, WEEKEND.getDiscount(counts.get(MAIN_DISH)));
            }
        }
    }
}