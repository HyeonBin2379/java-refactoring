package baseball.controller;

import static baseball.Constants.GameIOMessage.GAME_FINISHED;
import static baseball.Constants.GameIOMessage.GAME_START;
import static baseball.Constants.MenuNumber.FINISH;
import static baseball.util.Validation.validateMenuNumber;

import baseball.model.Computer;
import baseball.model.Rules;
import baseball.model.User;
import baseball.view.InputView;
import baseball.view.OutputView;
import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;

public class PlayController { // 숫자 야구 게임 진행 관련 클래스

    private final User user;
    private final Computer com;
    private final Rules rules;
    private final InputView inputView;
    private final OutputView outputView;

    public PlayController(InputView inputView, OutputView outputView) {
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

    public void startGame() {  // 게임 시작 단계
        System.out.println(GAME_START);
        com.setCom();
    }
    public void runGame() {    // 게임 진행 단계
        while (true) {
            user.setUser(retryUntilSuccess(inputView::inputUserNumber));
            rules.countBallsAndStrikes(user.getUser());
            if (rules.isThreeStrikes()) {
                outputView.printIfAnswer();
                break;
            }
            outputView.printIfNotAnswer(rules.getBalls(), rules.getStrikes());
            user.clearUser();
        }
    }
    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void restartOrFinishGame() {
        while (true) {
            int menuNum = validateMenuNumber(retryUntilSuccess(inputView::inputMenuNumber));
            if (menuNum == FINISH.getMenuNum()) {
                finishGame();
                return;
            }
            restartGame();
        }
    }

    private void restartGame() {
        resetGame();
        com.setCom();
        runGame();
    }

    private void finishGame() {
        System.out.println(GAME_FINISHED);
        resetGame();
        Console.close();
    }
    private void resetGame() {
        user.clearUser();
        com.clearCom();
    }
}
