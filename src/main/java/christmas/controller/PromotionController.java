package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {

    private final InputView inputView;
    private final OutputView outputView;
    public PromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printMenu();
        outputView.printBeforeDiscount();
        outputView.printGiveaway();
        outputView.printBenefitDetails();
        outputView.printTotalBenefit();
        outputView.printAfterDiscount();
        outputView.printEventBadge();
    }
}
