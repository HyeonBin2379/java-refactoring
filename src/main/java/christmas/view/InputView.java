package christmas.view;

import static christmas.constants.message.IOMessageConstants.DATE_INPUT;
import static christmas.constants.message.IOMessageConstants.MENU_ORDER_INPUT;
import static christmas.constants.message.IOMessageConstants.ORDER_EXAMPLE;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Validation;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public int inputDate() {
        System.out.println(DATE_INPUT);
        String input = Console.readLine();
        return validateDateInput(input);
    }

    public List<String> inputMenuOrder() {
        System.out.println(MENU_ORDER_INPUT + ORDER_EXAMPLE);
        String input = Console.readLine();
        validateOrderInput(input);
        return new ArrayList<>(List.of(input.split(",")));
    }
    private int validateDateInput(String input) {
        Validation.validateBlank(input);
        int validDate = Validation.validateInteger(input);
        Validation.validateDateRange(validDate);
        return validDate;
    }
    private void validateOrderInput(String input) {
        Validation.validateBlank(input);
        Validation.validateComma(input);
    }
}
