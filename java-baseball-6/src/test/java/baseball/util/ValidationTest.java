package baseball.util;

import static baseball.Constants.GameIOMessage.ERROR_FORMAT;
import static baseball.util.Validation.validateBlankOrSpace;
import static baseball.util.Validation.validateDuplicatedNumber;
import static baseball.util.Validation.validateInteger;
import static baseball.util.Validation.validateMenuNumber;
import static baseball.util.Validation.validateUserNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import baseball.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidationTest {

    @ParameterizedTest
    @DisplayName("입력값이 빈 문자열이거나 공백문자 포함할 시 예외 발생")
    @ValueSource(strings = {"123 ", "", " "})
    void validateBlankOrSpace_test(String input) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateBlankOrSpace(input));
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
    }

    @Test
    @DisplayName("입력값을 정수로 변환할 수 없으면 예외 발생")
    void validateInteger_test() {
        String input = "123.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateInteger(input));
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
    }

    @ParameterizedTest
    @DisplayName("사용자의 숫자가 유효하지 않으면 예외 발생")
    @ValueSource(strings = {"12", "1234", "103"})
    void validateUserNumber_test(String input) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateUserNumber(input));
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
    }

    @Test
    @DisplayName("사용자의 숫자 중 서로 중복된 숫자가 있으면 예외 발생")
    void validateDuplicatedNumber_test() {
        User sample = new User();
        sample.addValidNumberToArray("122");
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> validateDuplicatedNumber(sample.getUser())
        );
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
    }

    @ParameterizedTest
    @DisplayName("메뉴 번호가 유효하지 않으면 예외 발생")
    @ValueSource(strings = {"0", "3"})
    void validateMenuNumber_test(String input) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> validateMenuNumber(input));
        assertThat(exception.getMessage()).contains(ERROR_FORMAT);
    }
}