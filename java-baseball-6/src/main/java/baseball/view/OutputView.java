package baseball.view;

import static baseball.Constants.GameIOMessage.GAME_OVER;
import static baseball.Constants.GameIOMessage.THREE_STRIKES;

public class OutputView {
    private StringBuilder output;
    public void printIfAnswer() {
        output = new StringBuilder();
        output.append(THREE_STRIKES);
        output.append(GAME_OVER);
        System.out.print(output);
    }

    public void printIfNotAnswer(int balls, int strikes) {
        output = new StringBuilder();
        printNothing(balls, strikes);
        printBall(balls);
        printStrikes(strikes);
        System.out.println(output);   // 결과 출력 후 줄바꿈용
    }

    private void printNothing(int balls, int strikes) {
        if (balls == 0 && strikes == 0) {
            output.append("낫싱");
        }
    }

    private void printBall(int balls) {
        if (balls > 0) {
            output.append(balls).append("볼 ");
        }
    }
    private void printStrikes(int strikes) {
        if (strikes > 0) {
            output.append(strikes).append("스트라이크");
        }
    }
}
