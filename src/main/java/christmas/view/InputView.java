package christmas.view;

import static christmas.constants.message.IOMessageConstants.DATE_INPUT;
import static christmas.constants.message.IOMessageConstants.MENU_ORDER_INPUT;
import static christmas.constants.message.IOMessageConstants.ORDER_EXAMPLE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputDate() {
        System.out.println(DATE_INPUT);
        String input = Console.readLine();
        validateBlank(input);
        return input;
    }

    public String inputMenuOrder() {
        System.out.println(MENU_ORDER_INPUT+ORDER_EXAMPLE);
        String input = Console.readLine();
        validateBlank(input);
        validateComma(input);
        return input;
    }
    private void validateBlank(String input) {

    }
    private void validateComma(String input) {

    }
}
