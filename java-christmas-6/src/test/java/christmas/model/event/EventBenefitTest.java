package christmas.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.model.order.Order;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class EventBenefitTest {

    @ParameterizedTest
    @DisplayName("할인 전 총주문금액이 10,000원 이상 120,000원 미만일 때, 총혜택금액이 정확히 계산되는지 확인")
    @CsvSource(value = {
            "4, -3323", "8, -3723", "25, -6423",
            "26, -2023", "29, -2023", "31, -3023"
    })
    void getTotalBenefit_noGiveawayTest(int date, int expected) {
        Order orderSample = new Order("초코케이크-1,해산물파스타-1,타파스-1,제로콜라-1");
        EventBenefit eventSample = new EventBenefit(date, orderSample);
        Giveaway giveawaySample = new Giveaway(orderSample);

        giveawaySample.addMenu("샴페인", 1);
        eventSample.addBenefit(giveawaySample);
        assertEquals(eventSample.getTotalBenefit(), expected);
    }

    @ParameterizedTest
    @DisplayName("할인 전 총주문금액이 120,000원 이상일 때, 총혜택금액이 정확히 계산되는지 확인")
    @CsvSource(value = {
            "4, -28323", "8, -28723", "25, -31423",
            "26, -27023", "29, -27023", "31, -28023"
    })
    void getTotalBenefit_test(int date, int expected) {
        Order orderSample = new Order("초코케이크-1,티본스테이크-1,타파스-1,레드와인-1");
        EventBenefit eventSample = new EventBenefit(date, orderSample);
        Giveaway giveawaySample = new Giveaway(orderSample);

        giveawaySample.addMenu("샴페인", 1);
        eventSample.addBenefit(giveawaySample);
        assertEquals(eventSample.getTotalBenefit(), expected);
    }

    @ParameterizedTest
    @DisplayName("주어진 테스트케이스에서는 총혜택금액은 0원(적용 가능한 할인 및 증정 이벤트 없음)")
    @MethodSource("sampleTotalBenefitsZero")
    void getTotalBenefit_zeroBenefitTest(int date, String orders) {
        int expected = 0;
        Order orderSample = new Order(orders);
        EventBenefit eventSample = new EventBenefit(date, orderSample);
        Giveaway giveawaySample = new Giveaway(orderSample);

        giveawaySample.addMenu("샴페인", 1);
        eventSample.addBenefit(giveawaySample);
        assertEquals(eventSample.getTotalBenefit(), expected);
    }

    @ParameterizedTest
    @DisplayName("총혜택금액과 총할인금액이 동일할 때, 할인 후 총주문금액 계산")
    @CsvSource(value = {
            "4, 55177", "8, 54777", "25, 52077",
            "26, 56477", "29, 56477", "31, 55477"
    })
    void getAfterDiscounts_noGiveawayTest(int date, int expected) {
        Order orderSample = new Order("초코케이크-1,해산물파스타-1,타파스-1,제로콜라-1");
        EventBenefit eventSample = new EventBenefit(date, orderSample);
        Giveaway giveawaySample = new Giveaway(orderSample);

        giveawaySample.addMenu("샴페인", 1);
        eventSample.addBenefit(giveawaySample);

        int result = eventSample.getAfterDiscounts(orderSample.getTotalCost(), giveawaySample);
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @DisplayName("총혜택금액이 총할인금액보다 클 때, 할인 후 총주문금액 계산")
    @CsvSource(value = {
            "4, 132177", "8, 131777", "25, 129077",
            "26, 133477", "29, 133477", "31, 132477"
    })
    void getAfterDiscounts_test(int date, int expected) {
        Order orderSample = new Order("초코케이크-1,티본스테이크-1,타파스-1,레드와인-1");
        EventBenefit eventSample = new EventBenefit(date, orderSample);
        Giveaway giveawaySample = new Giveaway(orderSample);

        giveawaySample.addMenu("샴페인", 1);
        eventSample.addBenefit(giveawaySample);

        int result = eventSample.getAfterDiscounts(orderSample.getTotalCost(), giveawaySample);
        assertEquals(result, expected);
    }

    private static Stream<Arguments> sampleTotalBenefitsZero() {
        return Stream.of(
                Arguments.of(25, "타파스-1,제로콜라-1"),
                Arguments.of(26, "해산물파스타-1,타파스-1,제로콜라-1"),
                Arguments.of(28, "해산물파스타-1,타파스-1,제로콜라-1"),
                Arguments.of(29, "초코케이크-1,타파스-1,제로콜라-1"),
                Arguments.of(30, "초코케이크-1,타파스-1,제로콜라-1")
        );
    }
}