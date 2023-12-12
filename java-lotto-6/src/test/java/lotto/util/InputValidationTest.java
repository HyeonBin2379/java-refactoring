package lotto.util;

import static lotto.constants.ErrorMessage.ERROR_FORMAT;
import static lotto.constants.ErrorMessage.INVALID_COMMA;
import static lotto.util.InputValidation.validateComma;
import static lotto.util.InputValidation.validateInput;
import static lotto.util.InputValidation.validateInputWinnerNum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidationTest {
    @ParameterizedTest
    @DisplayName("입력된 문자열에 빈 문자열 혹은 공백문자가 사용된 문자열 입력 시, 에러 메시지 발생(공통 유효성 검사)")
    @ValueSource(strings = {"", "  ", "1 000"})
    void validateInput_exceptionTest(String input) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateInput(input));
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
    }

    @ParameterizedTest
    @DisplayName("입력된 문자열이 빈 문자열이 아니고, 공백문자도 없음 때 정상 실행")
    @ValueSource(strings = {"1", "45", "1000"})
    void validateInput_normalTest(String input) {
        assertThatCode(() -> validateInput(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("입력된 당첨번호가 빈 문자열이거나, 공백문자가 사용되었거나, 쉼표(,)를 잘못 사용했을 시 에러메시지 발생")
    @ValueSource(strings = {
            "1, 2, 3, 4, 5, 6", "1,,2,3,4,5,6", ",1,2,3,4,5,6",
            "1,2,3,4,5,6,", " 1,2,3,4,5,6", "123456"
    })
    void validateInputWInnerNum_test(String input) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateInputWinnerNum(input));
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
        assertThatCode(() -> validateInputWinnerNum("1,2,3,4,5,6")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("쉼표(,)가 입력된 문자열의 처음 또는 마지막에 오거나 연속으로 사용되면 에러 메시지 출력")
    @ValueSource(strings = {",1,2,3,4,5,6", "1,2,3,4,5,6,", "1,,2,3,4,5,6", "1,2,,3,4,,5,6"})
    void validateComma_test(String input) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateComma(input));
        assertEquals(String.format(ERROR_FORMAT + INVALID_COMMA + input), exception.getMessage());
        assertThatCode(() -> validateComma("1,2,3,4,5,6")).doesNotThrowAnyException();
    }
}