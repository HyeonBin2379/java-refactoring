package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.EventBenefit;
import christmas.model.Giveaway;
import christmas.model.ReservedDate;
import christmas.model.menu.Menu;
import christmas.util.OrderValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Collections;
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
        int beforeDiscount = getTotalCost(totalOrder);
        outputView.printPreview(date.getDate());
        outputView.printOrderedMenu(totalOrder);
        outputView.printBeforeDiscount(beforeDiscount);

        Giveaway giveaway = new Giveaway();
        EventBenefit benefit = new EventBenefit(date.getDate(), totalOrder);
        benefit.getEventDiscount(beforeDiscount, giveaway);
        int totalBenefit = benefit.getTotalBenefit();
        int afterDiscount = beforeDiscount - totalBenefit + giveaway.getTotalSum();
        outputView.printGiveaway(giveaway.getTable());
        outputView.printBenefitDetails(benefit.getEventTable());
        outputView.printTotalBenefit(totalBenefit);
        outputView.printAfterDiscount(afterDiscount);
        outputView.printEventBadge(totalBenefit);
        Console.close();
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
                int totalCounts = getTotalCounts(totalOrder);
                OrderValidator.validateTotalOrder(totalCounts, totalOrder);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return Collections.unmodifiableMap(totalOrder);
    }
    public Map<Menu, Integer> getTotalOrder(List<String> allOrders) {
        Map<Menu, Integer> totalOrder = new EnumMap<>(Menu.class);
        for (String order : allOrders) {
            String[] tokens = OrderValidator.validateDash(order);
            Menu menuName = OrderValidator.validateMenuName(tokens[0], totalOrder);
            int quantity = OrderValidator.validateQuantity(tokens[1]);
            totalOrder.put(menuName, quantity);
        }
        return Collections.unmodifiableMap(totalOrder);
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