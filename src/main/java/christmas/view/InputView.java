package christmas.view;

import static christmas.constants.message.IOMessageConstants.DATE_INPUT;
import static christmas.constants.message.IOMessageConstants.MENU_ORDER_INPUT;
import static christmas.constants.message.IOMessageConstants.ORDER_EXAMPLE;
import static christmas.validator.InputStringValidator.validateDateString;
import static christmas.validator.InputStringValidator.validateOrderString;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.order.OrderDate;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public OrderDate inputDate() {
        System.out.println(DATE_INPUT);
        String input = Console.readLine();
        validateDateString(input);
        return new OrderDate(input);
    }
    public List<String> inputMenuOrder() {
        System.out.println(MENU_ORDER_INPUT + ORDER_EXAMPLE);
        String input = Console.readLine();
        validateOrderString(input);
        return new ArrayList<>(List.of(input.split(",")));
    }
}
