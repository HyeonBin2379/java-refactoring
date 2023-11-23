package baseball;

import baseball.model.Play;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Play play = new Play(new InputView(), new OutputView());
        play.numberBaseBallGame();
    }
}