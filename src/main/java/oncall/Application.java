package oncall;

import oncall.controller.MainController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MainController oncall = new MainController(new InputView(), new OutputView());
        oncall.run();
    }
}
