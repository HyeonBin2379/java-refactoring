package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;
import lotto.constants.LottoRanks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatingTest {
    @ParameterizedTest
    @DisplayName("수익률의 계산 결과가 올바른지 확인")
    @MethodSource("sampleForReturnRate")
    void getReturnRate_test(Map<LottoRanks, Integer> input, int totalCost, double expected) {
        Calculating sample = new Calculating(input, totalCost);
        assertThat(sample.getRate()).isEqualTo(expected);
    }

    private static Stream<Arguments> sampleForReturnRate() {
        Map<LottoRanks, Integer> input = Map.of(
                LottoRanks.NONE, 0, LottoRanks.FIFTH, 1, LottoRanks.FOURTH, 1,
                LottoRanks.THIRD, 1, LottoRanks.SECOND, 0, LottoRanks.FIRST, 0
        );
        return Stream.of(
                Arguments.of(input, 2000000, 77.75),
                Arguments.of(input, 10000, 15550.0)
        );
    }
}