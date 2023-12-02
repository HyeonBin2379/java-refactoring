package baseball.Constants;

import java.util.Arrays;

public enum MenuNumber {
    RESTART(1),
    FINISH(2),
    NONE(0);

    private final int menuNum;

    MenuNumber(int menuNum) {
        this.menuNum = menuNum;
    }
    public static MenuNumber findMenuNumber(int number) {
        return Arrays.stream(MenuNumber.values())
                .filter(menuNumber -> menuNumber.menuNum == number)
                .findAny()
                .orElse(NONE);
    }

    public int getMenuNum() {
        return menuNum;
    }
}
