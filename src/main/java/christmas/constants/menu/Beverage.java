package christmas.constants.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum Beverage implements Menu{
    COKE("제로콜라", count->(count*3000)),
    WINE("레드와인", count->(count*6000)),
    CHAMPAIGN("샴페인", count->(count*25000)),
    NONE("없음", count -> (0));

    private final String menu;
    private final Function<Integer, Integer> price;

    Beverage(String menu, Function<Integer, Integer> price) {
        this.menu = menu;
        this.price = price;
    }

    public int getTotalCounts() {
        int sum = 0;
        return sum;
    }
    public int getTotalPrices(int counts) {
        return price.apply(counts);
    }
}
