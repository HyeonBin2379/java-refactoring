package christmas.model.menu;

import static christmas.constants.others.MarksAndConstants.INITIAL_VALUE_ZERO;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public enum MenuGroup {
    APPETIZER("에피타이저", Arrays.asList(Menu.SOUP, Menu.TAPAS, Menu.SALAD)),
    MAIN_DISH("메인", Arrays.asList(Menu.T_STAKE, Menu.BARBEQUE_RIB, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCO_CAKE, Menu.ICE_CREAM)),
    BEVERAGE("음료", Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE)),
    NONE("해당없음", Collections.EMPTY_LIST);

    private final String groupName;
    private final List<Menu> menuNames;

    MenuGroup(String groupName, List<Menu> menuNames) {
        this.groupName = groupName;
        this.menuNames = menuNames;
    }
    public static Map<MenuGroup, Integer> getCountsByGroup(Map<Menu, Integer> orderTable) {
        Map<MenuGroup, Integer> countTable = initializeCountTable();
        for (Menu menuName : orderTable.keySet()) {
            MenuGroup group = findMenuGroup(menuName.getName());
            int counts = countTable.getOrDefault(group, INITIAL_VALUE_ZERO);
            countTable.put(group, counts+orderTable.get(menuName));
        }
        return countTable;
    }
    private static Map<MenuGroup, Integer> initializeCountTable() {
        Map<MenuGroup, Integer> countTable = new EnumMap<>(MenuGroup.class);
        for (MenuGroup group : MenuGroup.values()) {
            if (group == NONE) {
                continue;
            }
            countTable.put(group, INITIAL_VALUE_ZERO);
        }
        return countTable;
    }
    public static MenuGroup findMenuGroup(String name) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasMenu(name))
                .findFirst()
                .orElse(NONE);
    }
    private boolean hasMenu(String name) {
        return menuNames.stream()
                .anyMatch(menu -> menu.equals(Menu.findMenuName(name)));
    }

    public String getGroupName() {
        return groupName;
    }
    public List<Menu> getList() {
        return menuNames;
    }
}