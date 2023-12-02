package baseball.view;

import static baseball.Constants.GameIOMessage.INPUT_MENU_NUMBER;
import static baseball.Constants.GameIOMessage.INPUT_USER_NUMBER;
import static baseball.util.Validation.validateBlankOrSpace;
import static baseball.util.Validation.validateInteger;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputUserNumber() {
        System.out.print(INPUT_USER_NUMBER);
        String input = Console.readLine();
        validateInput(input);
        return input;
    }
    public String inputMenuNumber() {
        System.out.print(INPUT_MENU_NUMBER);
        String input = Console.readLine();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        validateBlankOrSpace(input);
        validateInteger(input);
    }
}
