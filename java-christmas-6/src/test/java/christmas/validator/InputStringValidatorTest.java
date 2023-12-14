package christmas.validator;

import static christmas.validator.InputStringValidator.validateBlankOrSpace;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputStringValidatorTest {

    @ParameterizedTest
    @DisplayName("입력된 문자열이 빈 문자열 혹은 공백문자가 포함된 문자열이면 예외 발생")
    @ValueSource(strings = {"", " ", "해산물파스타-1, 레드와인-1", "12 "})
    void validateBlankOrSpace_test(String input) {
        assertThatThrownBy(() -> validateBlankOrSpace(input)).isInstanceOf(IllegalArgumentException.class);
    }
}