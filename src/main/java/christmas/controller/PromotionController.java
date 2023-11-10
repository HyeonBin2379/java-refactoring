package christmas.controller;

import static christmas.model.Event.CHRISTMAS;
import static christmas.model.Event.GIVEAWAY;
import static christmas.model.Event.SPECIAL;
import static christmas.model.Event.WEEKDAY;
import static christmas.model.Event.WEEKEND;
import static christmas.model.menu.MenuGroup.DESSERT;
import static christmas.model.menu.MenuGroup.MAIN_DISH;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Event;
import christmas.model.ReservedDate;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuGroup;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;
    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStart();
        ReservedDate date = getValidDate();
        Map<Menu, Integer> totalOrder = getValidTotalOrder();
        int beforeDiscount = getValidTotalCost(totalOrder);
        Console.close();

        Map<Menu, Integer> giveAwayMenu = getGiveAwayMenu(beforeDiscount);
        int totalGiveAway = 0;
        for (Menu menu : giveAwayMenu.keySet()) {
            totalGiveAway += menu.getPrice()*giveAwayMenu.get(menu);
        }

        Map<MenuGroup, Integer> countTable = MenuGroup.getCountsByGroup(totalOrder);
        Map<Event, Integer> eventTable = new LinkedHashMap<>();
        if (beforeDiscount >= 10000) {
            if (date.getDate() <= 25) {
                int discount = -(900 + 100 * date.getDate());
                eventTable.put(CHRISTMAS, discount);
            }
            if (date.getDate() % 7 == 3 || date.getDate() == 25) {
                eventTable.put(SPECIAL, -1000);
            }
            if (date.getDate() % 7 > 0 || date.getDate() % 7 < 3) {
                eventTable.put(WEEKEND, -2023*countTable.get(MAIN_DISH));
            }
            if (date.getDate() % 7 <= 0 && date.getDate() % 7 >= 3) {
                eventTable.put(WEEKDAY, -2023*countTable.get(DESSERT));
            }
            if (beforeDiscount >= 120000) {
                eventTable.put(GIVEAWAY, -totalGiveAway);
            }
        }

        int totalBenefit = 0;
        for (Event eventName : eventTable.keySet()) {
            totalBenefit -= eventTable.get(eventName);
        }

        int afterDiscount = beforeDiscount - totalBenefit + totalGiveAway;

        outputView.printPreview(date.getDate());
        outputView.printOrderedMenu(totalOrder);
        outputView.printBeforeDiscount(beforeDiscount);
        outputView.printGiveaway(giveAwayMenu);
        outputView.printBenefitDetails(eventTable);
        outputView.printTotalBenefit(totalBenefit);
        outputView.printAfterDiscount(afterDiscount);
        outputView.printEventBadge(totalBenefit);
    }

    public ReservedDate getValidDate() {
        ReservedDate date;
        while (true) {
            try {
                date = inputView.inputDate();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }
    public Map<Menu, Integer> getValidTotalOrder() {
        Map<Menu, Integer> totalOrder;
        while (true) {
            try {
                totalOrder = getTotalOrder(inputView.inputMenuOrder());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return totalOrder;
    }
    public int getValidTotalCost(Map<Menu, Integer> totalOrder) {
        int totalCost = 0;
        while (true) {
            try {
                int totalCounts = getTotalCounts(totalOrder);
                totalCost = getTotalCost(totalOrder);
                OrderValidator.validateTotalOrder(totalCounts, totalOrder);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                totalOrder.clear();
                totalOrder = getValidTotalOrder();
            }
        }
        return totalCost;
    }
    public Map<Menu, Integer> getTotalOrder(List<String> allOrders) {
        Map<Menu, Integer> totalOrder = new EnumMap<>(Menu.class);
        for (String order : allOrders) {
            String[] tokens = OrderValidator.validateDash(order);
            Menu menuName = OrderValidator.validateMenuName(tokens[0], totalOrder);
            int quantity = OrderValidator.validateQuantity(tokens[1]);
            totalOrder.put(menuName, quantity);
        }
        return totalOrder;
    }
    public int getTotalCounts(Map<Menu, Integer> totalOrder) {
        int totalCounts = 0;
        for (Menu menuName : totalOrder.keySet()) {
            totalCounts += totalOrder.get(menuName);
        }
        return totalCounts;
    }
    public int getTotalCost(Map<Menu, Integer> totalOrder) {
        int totalCost = 0;
        for (Menu menuName : totalOrder.keySet()) {
            totalCost += menuName.getPrice() * totalOrder.get(menuName);
        }
        return totalCost;
    }
    public Map<Menu, Integer> getGiveAwayMenu(int totalCost) {
        Map<Menu, Integer> giveAwayMenu = new EnumMap<>(Menu.class);
        if (totalCost >= 120000) {
            giveAwayMenu.put(Menu.CHAMPAIGN, 1);
        }
        return giveAwayMenu;
    }
}
