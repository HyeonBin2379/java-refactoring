package christmas.view;

import static christmas.constants.message.IOMessageConstants.DATE_INPUT;
import static christmas.constants.message.IOMessageConstants.MENU_ORDER_INPUT;
import static christmas.constants.message.IOMessageConstants.ORDER_EXAMPLE;
import static christmas.constants.others.MarksAndConstants.MONTH;
import static christmas.validator.InputStringValidator.validateDateString;
import static christmas.validator.InputStringValidator.validateOrderString;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;


public class InputView {

    public OrderDate inputDate() {
        System.out.printf(DATE_INPUT, MONTH);
        String input = Console.readLine();
        validateDateString(input);
        return new OrderDate(input);
    }

    public Order inputMenuOrder() {
        System.out.println(MENU_ORDER_INPUT + ORDER_EXAMPLE);
        String input = Console.readLine();
        validateOrderString(input);
        return new Order(input);
    }
}
