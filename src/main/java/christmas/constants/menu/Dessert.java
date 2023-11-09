package christmas.constants.menu;

import java.util.function.Function;

public enum Dessert implements Menu{

    CAKE("초코케이크", count->(count*15000)),
    ICE_CREAM("아이스크림", count->(count*5000)),
    NONE("없음", count->(0));

    private final String menu;
    private final Function<Integer, Integer> price;

    Dessert(String menu, Function<Integer, Integer> price) {
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
