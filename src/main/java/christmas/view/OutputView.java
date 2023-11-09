package christmas.view;

import static christmas.constants.message.MessageFormat.PREVIEW_TITLE_FORMAT;
import static christmas.constants.message.Title.AFTER_DISCOUNT;
import static christmas.constants.message.Title.BEFORE_DISCOUNT;
import static christmas.constants.message.Title.BENEFIT_DETAIL;
import static christmas.constants.message.Title.EVENT_BADGE;
import static christmas.constants.message.Title.GIVEAWAY;
import static christmas.constants.message.Title.MENU;
import static christmas.constants.message.Title.TOTAL_BENEFIT;

public class OutputView {
    public void printPreview(int date) {
        System.out.printf(PREVIEW_TITLE_FORMAT, date);
    }
    public void printMenu() {
        System.out.println(MENU.getTitle());
    }

    public void printBeforeDiscount() {
        System.out.println(BEFORE_DISCOUNT.getTitle());
    }

    public void printGiveaway() {
        System.out.println(GIVEAWAY.getTitle());
    }

    public void printBenefitDetails() {
        System.out.println(BENEFIT_DETAIL.getTitle());
    }

    public void printTotalBenefit() {
        System.out.println(TOTAL_BENEFIT.getTitle());
    }

    public void printAfterDiscount() {
        System.out.println(AFTER_DISCOUNT.getTitle());
    }

    public void printEventBadge() {
        System.out.println(EVENT_BADGE.getTitle());
    }
}
