package christmas.view;

import static christmas.constants.message.ErrorMessage.INVALID_DATE;
import static christmas.constants.message.ErrorMessage.INVALID_ORDER;
import static christmas.constants.message.IOMessageConstants.DATE_INPUT;
import static christmas.constants.message.IOMessageConstants.MENU_ORDER_INPUT;
import static christmas.constants.message.IOMessageConstants.ORDER_EXAMPLE;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validation;

public class InputView {

    public String inputDate() {
        System.out.println(DATE_INPUT);
        String input = Console.readLine();
        validateDateInput(input);
        return input;
    }

    public String inputMenuOrder() {
        System.out.println(MENU_ORDER_INPUT + ORDER_EXAMPLE);
        String input = Console.readLine();
        validateOrderInput(input);
        return input;
    }
    private void validateDateInput(String input) {
        Validation.validateBlank(input);
    }
    private void validateOrderInput(String input) {
        Validation.validateBlank(input);
        Validation.validateComma(input);
    }
}
