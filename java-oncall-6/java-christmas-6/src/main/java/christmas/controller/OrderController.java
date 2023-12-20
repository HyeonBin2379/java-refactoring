package christmas.controller;

import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class OrderController {

    private final InputView inputView;
    private final OutputView outputView;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public BenefitController start() {
        outputView.printStart();
        OrderDate date = retryUntilSuccess(inputView::inputDate);
        Order orderedMenu = retryUntilSuccess(inputView::inputMenuOrder);
        outputView.printOrderDetail(date, orderedMenu);
        return new BenefitController(outputView, date, orderedMenu);
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
