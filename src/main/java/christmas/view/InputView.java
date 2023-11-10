package christmas.view;

import static christmas.constants.message.ErrorMessage.BLANK_INPUT;
import static christmas.constants.message.IOMessageConstants.DATE_INPUT;
import static christmas.constants.message.IOMessageConstants.MENU_ORDER_INPUT;
import static christmas.constants.message.IOMessageConstants.ORDER_EXAMPLE;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.ReservedDate;
import christmas.util.OrderValidator;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public ReservedDate inputDate() {
        System.out.println(DATE_INPUT);
        String input = Console.readLine();
        validateDateInput(input);
        return new ReservedDate(input);
    }
    public List<String> inputMenuOrder() {
        System.out.println(MENU_ORDER_INPUT + ORDER_EXAMPLE);
        String input = Console.readLine();
        validateOrderInput(input);
        return new ArrayList<>(List.of(input.split(",")));
    }
    private void validateOrderInput(String input) {
        validateBlankOrSpace(input);
        OrderValidator.validateComma(input);
    }
    private void validateDateInput(String input) {
        validateBlankOrSpace(input);
    }
    private static void validateBlankOrSpace(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT.getErrorMsg());
        }
        if (input.contains(" ")) {
            throw new IllegalArgumentException(BLANK_INPUT.getErrorMsg());
        }
    }
}
