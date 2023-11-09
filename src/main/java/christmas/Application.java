package christmas;

import christmas.controller.PromotionController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        PromotionController promotion = new PromotionController(inputView, outputView);
        promotion.run();
    }
}
