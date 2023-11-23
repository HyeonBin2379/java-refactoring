package baseball.view;

public class OutputView {
    public void printIfAnswer() {
        System.out.println("3스트라이크");
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    public void printIfNotAnswer(int balls, int strikes) {
        printNothing(balls, strikes);
        printBall(balls);
        printStrikes(strikes);
        System.out.println();   // 결과 출력 후 줄바꿈용
    }

    private void printNothing(int balls, int strikes) {
        if (balls == 0 && strikes == 0) {
            System.out.print("낫싱");
        }
    }

    // 볼 또는 스트라이크 개수 출력(둘 다 1개 이상일 시 모두 출력)
    private void printBall(int balls) {
        if (balls > 0) {
            System.out.print(balls + "볼 ");
        }
    }
    private void printStrikes(int strikes) {
        if (strikes > 0) {
            System.out.print(strikes + "스트라이크");
        }
    }
}
