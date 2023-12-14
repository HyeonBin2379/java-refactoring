package christmas.model.event;

import static christmas.model.event.EventName.CHRISTMAS;
import static christmas.model.event.EventName.SPECIAL;
import static christmas.model.event.EventName.WEEKDAY;
import static christmas.model.event.EventName.WEEKEND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.model.order.Order;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountsTest {

    @ParameterizedTest
    @DisplayName("주어진 테스트케이스는 제공 가능한 할인 혜택이 없음")
    @MethodSource("sampleNotDiscounted")
    void getDiscountBenefit_notAddTest(int dateInput, String givenOrder) {
        Order orderSample = new Order(givenOrder);
        EventBenefit eventSample = new EventBenefit(dateInput, orderSample);
        Discounts discounts = new Discounts(dateInput, orderSample);

        discounts.getDiscountBenefit(eventSample.getEventTable());
        Map<EventName, Integer> result = eventSample.getEventTable();
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 10,000원 이상일 때, 1일부터 25일까지만 크리스마스 디데이 할인 적용")
    @MethodSource("sampleChristmasDiscount")
    void getChristmasDiscount_addTest(int dateInput, String givenOrder, boolean expected) {
        Order orderSample = new Order(givenOrder);
        EventBenefit eventSample = new EventBenefit(dateInput, orderSample);
        Discounts discounts = new Discounts(dateInput, orderSample);

        discounts.getDiscountBenefit(eventSample.getEventTable());
        Map<EventName, Integer> result = eventSample.getEventTable();
        assertEquals(result.containsKey(CHRISTMAS), expected);
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 10,000원 이상일 때, 매주 일요일 또는 크리스마스에만 특별 할인 적용")
    @ValueSource(ints = {3,10,17,24,25,31})
    void getSpecialDiscount_addTest(int dateInput) {
        Order orderSample = new Order("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        EventBenefit eventSample = new EventBenefit(dateInput, orderSample);
        Discounts discounts = new Discounts(dateInput, orderSample);

        discounts.getDiscountBenefit(eventSample.getEventTable());
        Map<EventName, Integer> result = eventSample.getEventTable();
        assertTrue(result.containsKey(SPECIAL));
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 10,000원 이상일 때, 일요일도, 크리스마스도 아니면 특별 할인 미적용")
    @ValueSource(ints = {2,9,16,23,26})
    void getSpecialDiscount_notAddTest(int dateInput) {
        Order orderSample = new Order("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        EventBenefit eventSample = new EventBenefit(dateInput, orderSample);
        Discounts discounts = new Discounts(dateInput, orderSample);

        discounts.getDiscountBenefit(eventSample.getEventTable());
        Map<EventName, Integer> result = eventSample.getEventTable();
        assertFalse(result.containsKey(SPECIAL));
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 10,000원 이상일 때, 일요일~목요일에 주문한 디저트 메뉴에 대해 평일 할인 적용")
    @MethodSource("sampleWeekdayDiscount")
    void getWeekdayDiscount_addTest(int dateInput, String givenOrder, boolean expected) {
        Order orderSample = new Order(givenOrder);
        EventBenefit eventSample = new EventBenefit(dateInput, orderSample);
        Discounts discounts = new Discounts(dateInput, orderSample);

        discounts.getDiscountBenefit(eventSample.getEventTable());
        Map<EventName, Integer> result = eventSample.getEventTable();
        assertEquals(result.containsKey(WEEKDAY), expected);
    }

    @ParameterizedTest
    @DisplayName("총주문금액이 10,000원 이상일 때, 금요일~토요일에 주문한 메인 요리에 대해 주말 할인 적용")
    @MethodSource("sampleWeekendDiscount")
    void getWeekendDiscount_addTest(int dateInput, String givenOrder, boolean expected) {
        Order orderSample = new Order(givenOrder);
        EventBenefit eventSample = new EventBenefit(dateInput, orderSample);
        Discounts discounts = new Discounts(dateInput, orderSample);

        discounts.getDiscountBenefit(eventSample.getEventTable());
        Map<EventName, Integer> result = eventSample.getEventTable();
        assertEquals(result.containsKey(WEEKEND), expected);
    }

    private static Stream<Arguments> sampleNotDiscounted() {
        return Stream.of(
                Arguments.of(25, "타파스-1,제로콜라-1"),
                Arguments.of(26, "해산물파스타-1,타파스-1,제로콜라-1"),
                Arguments.of(27, "해산물파스타-1,타파스-1,제로콜라-1"),
                Arguments.of(28, "해산물파스타-1,타파스-1,제로콜라-1"),
                Arguments.of(29, "초코케이크-1,타파스-1,제로콜라-1"),
                Arguments.of(30, "초코케이크-1,타파스-1,제로콜라-1")
        );
    }
    private static Stream<Arguments> sampleChristmasDiscount() {
        return Stream.of(
                Arguments.of(1, "해산물파스타-1", true),
                Arguments.of(25, "해산물파스타-1", true),
                Arguments.of(26, "해산물파스타-1", false)
        );
    }

    private static Stream<Arguments> sampleWeekdayDiscount() {
        return Stream.of(
                Arguments.of(3, "초코케이크-2,타파스-1,제로콜라-1", true),
                Arguments.of(7, "초코케이크-2,타파스-1,제로콜라-1", true),

                Arguments.of(2, "초코케이크-2,타파스-1,제로콜라-1", false),
                Arguments.of(3, "해산물파스타-2,타파스-1,제로콜라-1", false),
                Arguments.of(7, "해산물파스타-2,타파스-1,제로콜라-1", false),
                Arguments.of(8, "초코케이크-2,타파스-1,제로콜라-1", false)
        );
    }

    private static Stream<Arguments> sampleWeekendDiscount() {
        return Stream.of(
                Arguments.of(8, "해산물파스타-2,타파스-1,제로콜라-1", true),
                Arguments.of(9, "해산물파스타-2,타파스-1,제로콜라-1", true),

                Arguments.of(7, "해산물파스타-2,타파스-1,제로콜라-1", false),
                Arguments.of(8, "초코케이크-2,타파스-1,제로콜라-1", false),
                Arguments.of(9, "초코케이크-2,타파스-1,제로콜라-1", false),
                Arguments.of(10, "해산물파스타-2,타파스-1,제로콜라-1", false)
        );
    }
}