package christmas.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.model.menu.Menu;
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
    void addMenu_test(String menuName, int quantity) {
        Giveaway given = new Giveaway(120000);
        Map<Menu, Integer> expected = Map.of(Menu.findMenuName("샴페인"), 1);

        given.addMenu(menuName, quantity);
        assertEquals(given.getGiveaway(), expected);
    }

    @ParameterizedTest
    @DisplayName("주어진 테스트케이스에서는 증정할 메뉴와 그 수량을 추가할 수 없음")
    @MethodSource("notAddGiveawaySample")
    void addMenu_notAddTest(int beforeDiscount, String menuName, int quantity) {
        Giveaway given = new Giveaway(beforeDiscount);

        given.addMenu(menuName, quantity);
        Map<Menu, Integer> result = given.getGiveaway();
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("증정할 메뉴의 가격의 총합이 정확히 계산되는지 확인")
    void getSum_test() {
        Giveaway given = new Giveaway(120000);
        int expected = 85000;

        given.addMenu("샴페인", 1);
        given.addMenu("레드와인", 1);
        assertEquals(given.getSum(), expected);
    }

    private static Stream<Arguments> addGiveawaySample() {
        return Stream.of(
                Arguments.of( "샴페인", 1)
        );
    }
    private static Stream<Arguments> notAddGiveawaySample() {
        return Stream.of(
                Arguments.of(119999, "샴페인", 1),
                Arguments.of(120000, "화이트와인", 1),
                Arguments.of(120000, "샴페인", 0)
        );
    }
}