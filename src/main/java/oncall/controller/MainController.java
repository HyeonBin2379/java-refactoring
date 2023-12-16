package oncall.controller;

import java.util.function.Supplier;
import oncall.model.MonthCalender;
import oncall.view.InputView;
import oncall.view.OutputView;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        MonthCalender calender = retryUntilSuccess(inputView::inputMonthAndDayOfWeek);
        //retryUntilSuccess(inputView::inputWorkersOnCall);
        outputView.printOnCall(calender);
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
