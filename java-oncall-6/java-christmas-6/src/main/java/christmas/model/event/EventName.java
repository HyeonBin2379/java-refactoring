package christmas.model.event;

import static christmas.constants.others.DaysOfWeek.isFriday;
import static christmas.constants.others.DaysOfWeek.isSaturday;
import static christmas.constants.others.MarksAndConstants.X_MAS;

import christmas.constants.others.DaysOfWeek;
import java.util.function.BiFunction;
import java.util.function.Function;

public enum EventName {
    CHRISTMAS("크리스마스 디데이 할인", date -> date <= 25, (date, menuCount) -> (-1) * (900 + 100 * date)),
    SPECIAL("특별 할인", date -> DaysOfWeek.isSunday(date) || date == X_MAS, (date, menuCount) -> -1000),
    WEEKDAY("평일 할인", date -> !isFriday(date) && !isSaturday(date), (date, dessert) -> (-2023) * dessert),
    WEEKEND("주말 할인", date -> isFriday(date) || isSaturday(date), (date, mainDish) -> (-2023) * mainDish),
    GIVEAWAY("증정 이벤트", date -> false, (date, menuCount) -> 0);

    private final String eventName;
    private final Function<Integer, Boolean> condition;
    private final BiFunction<Integer, Integer, Integer> discount;

    EventName(String eventName, Function<Integer, Boolean> condition, BiFunction<Integer, Integer, Integer> discount) {
        this.eventName = eventName;
        this.condition = condition;
        this.discount = discount;
    }

    public String getEventName() {
        return eventName;
    }
    public boolean canDiscount(int date) {
        return condition.apply(date);
    }
    public int getDiscount(int date, int count) {
        return discount.apply(date, count);
    }
}
