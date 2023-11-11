package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Giveaway;
import christmas.model.Order;
import christmas.model.OrderDate;
import christmas.model.event.EventBenefit;
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
        OrderDate date = getValidDate();
        Map<Menu, Integer> totalOrder = getValidOrder(order);
        int beforeDiscount = order.getTotalCost();

        outputView.printOrderDetail(date.getDate(), totalOrder, beforeDiscount);
        getEventBenefits(date.getDate(), totalOrder, beforeDiscount);
        Console.close();
    }
    private void getEventBenefits(int date, Map<Menu, Integer> totalOrder, int beforeDiscount) {
        Giveaway giveaway = new Giveaway();
        EventBenefit benefit = new EventBenefit(date, totalOrder);

        benefit.getEventDiscount(beforeDiscount, giveaway);
        outputView.printAllEventsDetails(giveaway, benefit, beforeDiscount);
    }
    private OrderDate getValidDate() {
        OrderDate date;
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
                order.setValidOrderTable(inputView.inputMenuOrder());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                order.clearOrder();
            }
        }
        return Collections.unmodifiableMap(order.getOrder());
    }
}