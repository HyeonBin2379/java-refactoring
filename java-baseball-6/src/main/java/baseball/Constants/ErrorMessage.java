package baseball.Constants;

import static baseball.Constants.GameIOMessage.ERROR_FORMAT;

public enum ErrorMessage {
    BLANK_INPUT("입력값은 빈 문자열입니다."),
    SPACE_INCLUDED("입력값은 공백문자를 포함할 수 없습니다: "),
    NOT_INTEGER("입력값은 정수로 변환할 수 없습니다: "),
    NOT_THREE_DIGIT("입력값은 3자리 숫자가 아닙니다: "),
    ZERO_INCLUDED("입력값은 0을 포함할 수 없습니다: "),
    DUPLICATED_NUMBER("입력값은 중복된 숫자를 포함할 수 없습니다."),
    INVALID_MENU("입력값은 유효한 메뉴번호가 아닙니다: ");

    private final String message;
    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format(ERROR_FORMAT + message);
    }
}
