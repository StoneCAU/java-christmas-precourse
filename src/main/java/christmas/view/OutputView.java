package christmas.view;

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
    }

    public static void printErrorMessage(String message) {
        printNewLine();
        System.out.println(message);
    }

    private static void printNewLine() {
        System.out.printf(NEW_LINE);
    }
}
