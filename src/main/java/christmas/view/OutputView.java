package christmas.view;

import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.Visit;

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
            return;
        }
        System.out.println(bonus.getMenu().getName() + " " + bonus.getQuantity() + "개");
        printNewLine();
    }

    public static void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
