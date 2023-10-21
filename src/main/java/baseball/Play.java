package baseball;

import camp.nextstep.edu.missionutils.Console;

public class Play {

    private final User user;
    private final Computer com;
    private final Rules rules;
    private String input;

    public Play() {
        user = new User();
        com = new Computer();
        rules = new Rules();
    }

    public void startGame() {
        System.out.println("숫자 야구 게임을 시작합니다.");
        com.setCom();
    }

    public void setInput(String msg) {
        System.out.print(msg);
        input = Console.readLine();
    }

    public void restartOrFinishGame() {
        while (true) {
            setInput("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.\n");
            if (Integer.parseInt(input) > 2 || Integer.parseInt(input) < 1) {
                throw new IllegalArgumentException("Invalid input: " + input);
            }
            if (input.equals("1")) {
                user.clearUser();
                com.clearCom();
                com.setCom();
                runGame();
            }
            if (input.equals("2")) {
                System.out.println("숫자 야구 게임을 종료합니다.");
                Console.close();
                break;
            }
        }
    }

    public void runGame() {
        while (true) {
            // 사용자 입력
            setInput("숫자를 입력해주세요 : ");
            user.setUser(input);
            // 스트라이크, 볼 개수 구하기
            rules.countBallAndStrikes(user.getUser(), com.getCom());
            // 결과 출력
            if (rules.isThreeStrikes()) {
                rules.printIfAnswer();
                break;
            }
            rules.printIfNotAnswer();
            user.clearUser();
        }
    }
}
