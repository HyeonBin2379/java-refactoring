package lotto.view;

import static lotto.constants.LottoIOMessage.BONUS_NUM_INPUT;
import static lotto.constants.LottoIOMessage.BUYING_COST_INPUT;
import static lotto.constants.LottoIOMessage.WINNING_NUM_INPUT;
import static lotto.util.InputValidation.validateInput;
import static lotto.util.InputValidation.validateInputWinnerNum;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputBuyingCost() {
        System.out.println(BUYING_COST_INPUT);
        String inputCost = Console.readLine();
        validateInput(inputCost);
        return inputCost;
    }

    public String inputWinnerNumbers() {
        System.out.println(WINNING_NUM_INPUT);
        String inputWinningNum = Console.readLine();
        validateInputWinnerNum(inputWinningNum);
        return inputWinningNum;
    }

    public String inputBonusNumber() {
        System.out.println(BONUS_NUM_INPUT);
        String inputBonusNum = Console.readLine();
        validateInput(inputBonusNum);
        return inputBonusNum;
    }

    public void finishInput() {
        Console.close();
    }
}