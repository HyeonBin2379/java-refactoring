package baseball.util;

import java.util.List;

public class Validation {

    public static void validateBlankOrSpace(String input) {
        validateBlank(input);
        validateSpace(input);
    }
    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
    private static void validateSpace(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateInteger(String input) {
        try {
            int temp = Integer.parseInt(input); // 입력값에 문자가 섞이면 Exception 발생
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("This input is not Integer: " + input);
        }
    }

    public static void validateUserNumber(String input) {
        validateUserNumberLength(input);    // 문자열 길이 검사
        validateNonZero(input);   // 0 포함 여부 검사
    }

    // 입력된 문자열의 길이가 3인지 검사
    private static void validateUserNumberLength(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException("Invalid input length.");
        }
    }

    // 입력된 문자열에 0이 포함되었는지 검사
    // 이 유효성검사는 3자리 숫자인 입력값을 대상으로 수행
    // 따라서 이 검사를 통과하는 문자열은 1~9 사이 숫자로만 구성된 3자리 수
    private static void validateNonZero(String input) {
        if (input.contains("0")) {
            throw new IllegalArgumentException("This input includes zero.");
        }
    }

    public static void validateDuplicatedNumber(List<Integer>user, int num) {
        if (user.contains(num)) {
            throw new IllegalArgumentException("This is already contained: " + num);
        }
    }
}
