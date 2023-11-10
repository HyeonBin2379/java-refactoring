package christmas.view;

import static christmas.constants.message.IOMessageConstants.START_MESSAGE;
import static christmas.constants.message.MessageFormat.BENEFIT_FORMAT;
import static christmas.constants.message.MessageFormat.MENU_FORMAT;
import static christmas.constants.message.MessageFormat.MONEY_FORMAT;
import static christmas.constants.message.MessageFormat.PREVIEW_TITLE_FORMAT;
import static christmas.constants.message.Title.AFTER_DISCOUNT;
import static christmas.constants.message.Title.BEFORE_DISCOUNT;
import static christmas.constants.message.Title.BENEFIT_DETAIL;
import static christmas.constants.message.Title.EVENT_BADGE;
import static christmas.constants.message.Title.GIVEAWAY;
import static christmas.constants.message.Title.MENU;
import static christmas.constants.message.Title.TOTAL_BENEFIT;
import static christmas.model.EventBadge.NONE;
import static christmas.model.EventBadge.SANTA;
import static christmas.model.EventBadge.STAR;
import static christmas.model.EventBadge.TREE;

import christmas.model.Event;
import christmas.model.menu.Menu;
import java.util.Map;

public class OutputView {
    public void printStart() {
        System.out.println(START_MESSAGE);
    }
    public void printPreview(int date) {
        System.out.printf(PREVIEW_TITLE_FORMAT, date);
    }
    public void printOrderedMenu(Map<Menu, Integer> totalOrder) {
        System.out.println(MENU.getTitle());
        for (Menu menu : totalOrder.keySet()) {
            System.out.printf(MENU_FORMAT, menu.getName(), totalOrder.get(menu));
        }
    }

    public void printBeforeDiscount(int totalCost) {
        System.out.println(BEFORE_DISCOUNT.getTitle());
        System.out.printf(MONEY_FORMAT, totalCost);
    }

    public void printGiveaway(Map<Menu, Integer> giveAwayMenu) {
        System.out.println(GIVEAWAY.getTitle());
        if (giveAwayMenu.isEmpty()) {
            System.out.println("없음");
            return;
        }

        for (Menu menu : giveAwayMenu.keySet()) {
            System.out.printf(MENU_FORMAT, menu.getName(), giveAwayMenu.get(menu));
        }
    }

    public void printBenefitDetails(Map<Event, Integer> totalEvent) {
        System.out.println(BENEFIT_DETAIL.getTitle());
        if (totalEvent.isEmpty()) {
            System.out.println("없음");
            return;
        }

        for (Event event : totalEvent.keySet()) {
            String money = String.format(MONEY_FORMAT, totalEvent.get(event));
            System.out.printf(BENEFIT_FORMAT, event.getEventName(), money);
        }
    }

    public void printTotalBenefit(int totalBenefit) {
        System.out.println(TOTAL_BENEFIT.getTitle());
        System.out.printf(MONEY_FORMAT, totalBenefit);
    }

    public void printAfterDiscount(int totalCost) {
        System.out.println(AFTER_DISCOUNT.getTitle());
        System.out.printf(MONEY_FORMAT, totalCost);
    }

    public void printEventBadge(int totalBenefit) {
        System.out.println(EVENT_BADGE.getTitle());
        if (totalBenefit >= 5000 && totalBenefit < 10000) {
            System.out.println(STAR.getBadge());
            return;
        }
        if (totalBenefit >= 10000 && totalBenefit < 20000) {
            System.out.println(TREE.getBadge());
            return;
        }
        if (totalBenefit >= 20000) {
            System.out.println(SANTA.getBadge());
            return;
        }
        System.out.println(NONE.getBadge());
    }
}
