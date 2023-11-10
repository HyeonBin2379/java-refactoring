package christmas.constants.menu;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    APPETIZER(Arrays.asList(Menu.SOUP, Menu.TAPAS, Menu.SALAD)),
    MAIN_DISH(Arrays.asList(Menu.T_STAKE, Menu.BARBEQUE_RIP, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT(Arrays.asList(Menu.CHOCO_CAKE, Menu.ICE_CREAM)),
    BEVERAGE(Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAIGN));

    private final List<Menu> menuNames;

    MenuGroup(List<Menu> menuNames) {
        this.menuNames = menuNames;
    }
    public List<Menu> getList() {
        return menuNames;
    }
}
