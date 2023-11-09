package christmas.constants.menu;

import java.util.function.Function;

public enum MainDish implements Menu{

    STAKE("티본스테이크", count->(count*55000)),
    BARBEQUE("바비큐립", count->(count*54000)),
    SEAFOOD_PASTA("해산물파스타", count->(count*35000)),
    CHRISTMAS_PASTA("크리스마스파스타", count->(count*25000)),
    NONE("없음", count->(0));

    private final String menu;
    private final Function<Integer, Integer> price;

    MainDish(String menu, Function<Integer, Integer> price) {
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
