package christmas.controller;

import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Collections;
import java.util.Map;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Order order;
    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.order = new Order();
    }

    public BenefitController start() {
        outputView.printStart();
        OrderDate date = getValidDate();
        Map<Menu, Integer> totalOrder = getValidOrder(order);
        int beforeDiscount = order.getTotalCost();
        outputView.printOrderDetail(date.getDate(), totalOrder, beforeDiscount);
        return new BenefitController(outputView, date, order);
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
