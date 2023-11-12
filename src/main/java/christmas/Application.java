package christmas;

import christmas.controller.DecemberEventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        DecemberEventController promotion = new DecemberEventController(inputView, outputView);
        promotion.run();
    }
}
