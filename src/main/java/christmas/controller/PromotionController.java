package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.EventBenefit;
import christmas.model.Giveaway;
import christmas.model.Order;
import christmas.model.ReservedDate;
import christmas.model.menu.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Collections;
import java.util.Map;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Order order;
    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.order = new Order();
    }

    public void run() {
        outputView.printStart();
        ReservedDate date = getValidDate();
        Map<Menu, Integer> totalOrder = getValidOrder(order);
        int beforeDiscount = order.getTotalCost();
        outputView.printOrderDetail(date.getDate(), totalOrder, beforeDiscount);

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

    private ReservedDate getValidDate() {
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
    private Map<Menu, Integer> getValidOrder(Order order) {
        while (true) {
            try {
                order.setOrderTable(inputView.inputMenuOrder());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                order.clearOrder();
            }
        }
        return Collections.unmodifiableMap(order.getOrder());
    }
}