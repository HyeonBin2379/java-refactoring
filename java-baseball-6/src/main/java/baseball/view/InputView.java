package baseball.view;

import static baseball.util.Validation.validateBlankOrSpace;
import static baseball.util.Validation.validateInteger;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String setInput(String msg) {   // 입력값을 문자열 형태로 저장
        System.out.print(msg);              // 입력 메시지 출력
        String input = Console.readLine();
        validateInput(input);
        return input;
    }

    private void validateInput(String input) {
        validateBlankOrSpace(input);
        validateInteger(input);            // 첫번째, 두번째 입력 모두 문자 없는 숫자만 필요
    }
}
