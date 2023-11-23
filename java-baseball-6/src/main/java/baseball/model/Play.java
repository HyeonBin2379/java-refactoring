package baseball.model;

import static baseball.util.Validation.validateInteger;

import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Console;

public class Play { // 숫자 야구 게임 진행 관련 클래스

    private final User user;
    private final Computer com;
    private final Rules rules;
    private final InputView inputView;
    private final OutputView outputView;

    public Play(InputView inputView, OutputView outputView) {
        this.user = new User();
        this.com = new Computer();
        this.rules = new Rules(com);
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void numberBaseBallGame() {  // 숫자 야구 게임 실행
        startGame();
        runGame();
        restartOrFinishGame();
    }

    private void startGame() {  // 게임 시작 단계
        System.out.println("숫자 야구 게임을 시작합니다.");
        com.setCom();
    }

    private void runGame() {    // 게임 진행 단계
        while (true) {
            user.setUser(inputView.setInput("숫자를 입력해주세요 : "));
            rules.countBallsAndStrikes(user.getUser());
            if (rules.isThreeStrikes()) {
                outputView.printIfAnswer();
                break;
            }
            outputView.printIfNotAnswer(rules.getBalls(), rules.getStrikes());
            user.clearUser();
        }
    }

    private void restartOrFinishGame() {    // 게임 재시작 or 종료 단계
        // 게임 재시작을 2회 이상 수행하려면 반복문 필수
        while (true) {
            int menuNum = checkMenuNumber(inputView.setInput("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.\n"));
            if (menuNum == 1) {
                restartGame();
            } else if (menuNum == 2) {
                finishGame();
                break;
            }
        }
    }

    // 재시작 메뉴 번호 입력 시 1, 2 이외의 숫자가 입력되면 IllegalArgumentException 발생
    private int checkMenuNumber(String input) {
        validateInteger(input);
        int menuNum = Integer.parseInt(input);
        if (menuNum < 1 || menuNum > 2) {
            throw new IllegalArgumentException("Invalid Menu Number: " + menuNum);
        }
        return menuNum;
    }

    private void restartGame() {
        user.clearUser();
        com.clearCom();
        com.setCom();
        runGame();
    }

    private void finishGame() {
        System.out.println("숫자 야구 게임을 종료합니다.");
        user.clearUser();
        com.clearCom();
        Console.close();
    }
}
