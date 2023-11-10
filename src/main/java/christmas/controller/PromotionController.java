package christmas.controller;

import static christmas.constants.message.ErrorMessage.INVALID_DATE;
import static christmas.constants.message.ErrorMessage.INVALID_ORDER;

import christmas.constants.menu.Menu;
import christmas.util.Validation;
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
        int date = getValidDate();
        Map<Menu, Integer> totalOrder = new EnumMap<>(Menu.class);
        try {
            totalOrder = getTotalOrder(inputView.inputMenuOrder());
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_ORDER.getErrorMsg());
        }
        int totalCost = getValidTotalCost(totalOrder);

        outputView.printPreview(date);
        outputView.printMenu();
        outputView.printBeforeDiscount();
        outputView.printGiveaway();
        outputView.printBenefitDetails();
        outputView.printTotalBenefit();
        outputView.printAfterDiscount();
        outputView.printEventBadge();
    }

    public int getValidDate() {
        int date;
        while (true) {
            try {
                date = inputView.inputDate();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_DATE.getErrorMsg());
            }
        }
        return date;
    }
    public Map<Menu, Integer> getValidTotalOrder() {
        Map<Menu, Integer> totalOrder;
        while (true) {
            try {
                List<String> allOrders = inputView.inputMenuOrder();
                totalOrder = getTotalOrder(allOrders);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_ORDER);
            }
        }
        return totalOrder;
    }
    public int getValidTotalCost(Map<Menu, Integer> totalOrder) {
        int totalCost = 0;
        try {
            int totalCounts = getTotalCounts(totalOrder);
            totalCost = getTotalCost(totalOrder);
            Validation.validateTotalOrder(totalCounts, totalOrder);
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_ORDER.getErrorMsg());
        }
        return totalCost;
    }
    public Map<Menu, Integer> getTotalOrder(List<String> allOrders) {
        Map<Menu, Integer> totalOrder = new EnumMap<>(Menu.class);
        for (String order : allOrders) {
            String[] tokens = Validation.validateDash(order);
            Menu menuName = Validation.validateMenuName(tokens[0], totalOrder);
            int quantity = Validation.validateQuantity(tokens[1]);
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
}
