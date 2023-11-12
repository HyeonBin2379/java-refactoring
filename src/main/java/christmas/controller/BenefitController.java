package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.event.EventBenefit;
import christmas.model.event.Giveaway;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.view.OutputView;

public class BenefitController {

    private final OutputView outputView;
    private final OrderDate date;
    private final Order totalOrder;

    public BenefitController(OutputView outputView, OrderDate date, Order totalOrder) {
        this.outputView = outputView;
        this.date = date;
        this.totalOrder = totalOrder;
    }

    public void finish() {
        getEventBenefits();
        Console.close();
    }
    public void getEventBenefits() {
        int beforeDiscount = totalOrder.getTotalCost();
        Giveaway giveaway = new Giveaway(beforeDiscount);
        EventBenefit benefit = new EventBenefit(date.getDate(), totalOrder.getOrder());

        giveaway.addMenu("샴페인", 1);
        benefit.addBenefit(beforeDiscount, giveaway);
        outputView.printAllEventsDetails(giveaway, benefit, beforeDiscount);
    }
}
