package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.ReservedDate;
import christmas.model.menu.Menu;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
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
        int totalCost = getValidTotalCost(totalOrder);
        Map<Menu, Integer> giveAwayMenu = getGiveAwayMenu(totalCost);
        int totalBenefit = 0;
        Console.close();

        outputView.printPreview(date.getDate());
        outputView.printOrderedMenu(totalOrder);
        outputView.printBeforeDiscount(totalCost);
        outputView.printGiveaway(giveAwayMenu);
        outputView.printBenefitDetails();
        outputView.printTotalBenefit(totalBenefit);
        outputView.printAfterDiscount(totalCost);
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
