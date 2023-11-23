package baseball.model;

import java.util.List;

public class Rules {    // 볼, 스트라이크 개수 규칙 관련 클래스

    private int balls;
    private int strikes;
    private final List<Integer> com;

    public Rules(Computer com) {
        initBallsAndStrikes();
        this.com = com.getCom();
    }

    public void countBallsAndStrikes(List<Integer> user) {
        initBallsAndStrikes();
        for (Integer userNum : user) {
            if (isNotContained(userNum)) {
                continue;
            }
            calculateBallsAndStrikes(userNum, user);
        }
    }
    // 볼, 스트라이크 개수 세기 전 항상 0으로 초기화
    private void initBallsAndStrikes() {
        this.balls = 0;
        this.strikes = 0;
    }
    private boolean isNotContained(int userNum) {
        return !com.contains(userNum);
    }

    public void calculateBallsAndStrikes(Integer userNum, List<Integer> user) {
        int c_idx = com.indexOf(userNum);   // 사용자의 숫자가 저장된 상대방의 인덱스
        int u_idx = user.indexOf(userNum);  // 사용자의 숫자가 저장된 사용자의 인덱스
        addBalls(userNum, c_idx, u_idx);
        addStrikes(userNum, c_idx, u_idx);
    }
    private void addBalls(Integer userNum, int c_idx, int u_idx) {
        if (userNum.equals(com.get(c_idx)) && c_idx != u_idx) { // 숫자는 같고 자리는 다름
            balls++;
        }
    }
    private void addStrikes(Integer userNum, int c_idx, int u_idx) {
        if (userNum.equals(com.get(c_idx)) && c_idx == u_idx) { // 숫자와 자리 모두 동일
            strikes++;
        }
    }

    public boolean isThreeStrikes() {
        return strikes == 3;
    }

    public int getBalls() {
        return balls;
    }
    public int getStrikes() {
        return strikes;
    }
}
