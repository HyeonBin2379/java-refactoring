package oncall;

import oncall.controller.MainController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MainController onCall = new MainController(new InputView(), new OutputView());
        onCall.run();
    }
}
