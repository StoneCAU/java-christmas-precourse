package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.Visit;
import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printPromotionTitle(Visit visit) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", visit.getDay());
        printNewLine();
    }

    public static void printOrders(Orders orders) {
        System.out.println("<주문 메뉴>");
        orders.getOrders().forEach(System.out::println);
        printNewLine();
    }

    public static void printTotalPriceBeforeDiscount(Orders orders) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(orders.getTotalPrice().toString());
        printNewLine();
    }

    public static void printPresentMenu(Orders orders) {
        System.out.println("<증정 메뉴>");
        Order bonus = Event.getBonus(orders.getTotalPrice().getPrice());
        if (bonus == null) {
            System.out.println("없음");
            printNewLine();
            return;
        }
        System.out.println(bonus.getMenu().getName() + " " + bonus.getQuantity() + "개");
        printNewLine();
    }

    public static void printDiscounts(Discount discount) {
        System.out.println("<혜택 내역>");
        List<Event> events = discount.getEvents();
        if (events.isEmpty()) {
            System.out.println("없음");
            printNewLine();
            return;
        }
        if (events.contains(Event.CHRISTMAS_D_DAY)) printChristmasDiscount(discount);
        if (events.contains(Event.WEEKDAY)) printWeekdayDiscount(discount);
        if (events.contains(Event.WEEKEND)) printWeekendDiscount(discount);
        if (events.contains(Event.SPECIAL)) printSpecialDiscount(discount);
        if (Event.getBonus(discount.getOrders().getTotalPrice().getPrice()) != null)
            printBonusDiscount(discount);
        printNewLine();
    }

    public static void printTotalDiscount(Discount discount) {
        System.out.println("<총혜택 금액>");
        if (discount.getTotalDiscount().getPrice() == 0) {
            System.out.println("0원");
            printNewLine();
            return;
        }
        System.out.println("-" + discount.getTotalDiscount());
        printNewLine();
    }

    public static void printAfterDiscount(Discount discount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(discount.getAfterDiscount());
        printNewLine();
    }

    public static void printBadge(Discount discount) {
        System.out.println("<12월 이벤트 배지>");
        Badge badge = Badge.getBadge(discount.getTotalDiscount().getPrice());
        System.out.println(badge.getName());
    }

    public static void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }

    private static void printChristmasDiscount(Discount discount) {
        System.out.printf("크리스마스 디데이 할인: -%s%n", Event.getChristmasDiscount(discount.getVisit().getDay()));
    }

    private static void printWeekdayDiscount(Discount discount) {
        System.out.printf("평일 할인: -%s%n", Event.getWeekdayDiscount(discount.getOrders()));
    }

    private static void printWeekendDiscount(Discount discount) {
        System.out.printf("주말 할인: -%s%n", Event.getWeekendDiscount(discount.getOrders()));
    }

    private static void printSpecialDiscount(Discount discount) {
        System.out.printf("특별 할인: -%s%n", Event.getSpecialDiscount(discount.getVisit().getDay()));
    }

    private static void printBonusDiscount(Discount discount) {
        System.out.printf("증정 이벤트: -%s%n", Event.getBonus(discount.getOrders().getTotalPrice().getPrice()).getMenu().getMoney());
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
