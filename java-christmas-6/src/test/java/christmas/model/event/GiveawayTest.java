package christmas.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.model.menu.Menu;
import christmas.model.order.Order;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GiveawayTest {
    @ParameterizedTest
    @DisplayName("주어진 테스트케이스에서는 증정할 메뉴와 그 수량 추가 가능")
    @MethodSource("addGiveawaySample")
    void addMenu_test(String givenOrder, String menuName, int quantity) {
        Map<Menu, Integer> expected = Map.of(Menu.findMenuName("샴페인"), 1);
        Order orderSample = new Order(givenOrder);
        Giveaway given = new Giveaway(orderSample);
        given.addMenu(menuName, quantity);
        assertEquals(given.getGiveaway(), expected);
    }

    @ParameterizedTest
    @DisplayName("주어진 테스트케이스에서는 증정할 메뉴와 그 수량을 추가할 수 없음")
    @MethodSource("notAddGiveawaySample")
    void addMenu_notAddTest(String givenOrder, String menuName, int quantity) {
        Order orderSample = new Order(givenOrder);
        Giveaway given = new Giveaway(orderSample);
        given.addMenu(menuName, quantity);
        assertEquals(given.getGiveaway(), Map.of());
    }

    @Test
    @DisplayName("할인 전 총주문금액이 120,000원 이상일 떄, 증정할 메뉴의 가격의 총합이 정확한지 확인")
    void getSum_test() {
        int expected = 85000;
        Order orderSample = new Order("양송이수프-1,바비큐립-1,레드와인-1");
        Giveaway given = new Giveaway(orderSample);
        given.addMenu("샴페인", 1);
        given.addMenu("레드와인", 1);
        assertEquals(given.getSum(), expected);
    }

    private static Stream<Arguments> addGiveawaySample() {
        return Stream.of(
                Arguments.of( "양송이수프-1,바비큐립-1,레드와인-1", "샴페인", 1)
        );
    }
    private static Stream<Arguments> notAddGiveawaySample() {
        return Stream.of(
                Arguments.of("양송이수프-1,바비큐립-1,아이스크림-1", "샴페인", 1),
                Arguments.of("양송이수프-1,바비큐립-1,레드와인-1", "화이트와인", 1),
                Arguments.of("양송이수프-1,바비큐립-1,레드와인-1", "샴페인", 0)
        );
    }
}