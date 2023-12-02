package baseball;

import baseball.controller.PlayController;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {

    public static void main(String[] args) {
        PlayController play = new PlayController(new InputView(), new OutputView());
        play.numberBaseBallGame();
    }
}