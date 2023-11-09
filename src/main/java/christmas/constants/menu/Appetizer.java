package christmas.constants.menu;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public enum Appetizer implements Menu {
    SOUP("양송이수프", count -> (count*6000)),
    TAPAS("타파스", count -> (count*5500)),
    SALAD("시저샐러드", count -> (count*8000)),
    NONE("없음", count -> (0));

    private final String menu;
    private final Function<Integer, Integer> price;

    Appetizer(String menu, Function<Integer, Integer> price) {
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
