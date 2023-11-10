package christmas.model.menu;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum MenuGroup {
    APPETIZER(Arrays.asList(Menu.SOUP, Menu.TAPAS, Menu.SALAD)),
    MAIN_DISH(Arrays.asList(Menu.T_STAKE, Menu.BARBEQUE_RIP, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT(Arrays.asList(Menu.CHOCO_CAKE, Menu.ICE_CREAM)),
    BEVERAGE(Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAIGN)),
    NONE(Collections.EMPTY_LIST);

    private final List<Menu> menuNames;

    MenuGroup(List<Menu> menuNames) {
        this.menuNames = menuNames;
    }
    public List<Menu> getList() {
        return menuNames;
    }
    public static Map<MenuGroup, Integer> getCountsByGroup(Map<Menu, Integer> orderTable) {
        Map<MenuGroup, Integer> countTable = new EnumMap<>(MenuGroup.class);
        for (Menu menuName : orderTable.keySet()) {
            MenuGroup group = findMenuGroup(menuName);
            countTable.put(group, countTable.getOrDefault(group,0)+1);
        }
        return countTable;
    }
    private static MenuGroup findMenuGroup(Menu menu) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasMenu(menu))
                .findAny()
                .orElse(NONE);
    }
    private boolean hasMenu(Menu target) {
        for (Menu menu : menuNames) {
            if (menu == target)
                return true;
        }
        return false;
    }
}
