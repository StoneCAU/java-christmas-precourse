package christmas.validator;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.Visit;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class InputValidator {
    public static Visit validateVisitDay(String input) {
        int day = parseNumber(input);
        validateDay(day);

        return new Visit(day);
    }

    public static Orders validateOrders(String input) {
        List<String> parsedOrders = parseOrders(input);
        List<Order> orderList = getOrderList(parsedOrders);
        validateOrderList(orderList);

        return new Orders(orderList);
    }

    private static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ChristmasException(ErrorMessage.INVALID_DAY);
        }
    }

    private static void validateDay(int day) {
        if (day < 1 || day > 31) {
            throw new ChristmasException(ErrorMessage.INVALID_DAY);
        }
    }

    private static List<String> parseOrders(String input) {
        return Arrays.stream(input.split(",")).toList();
    }

    private static List<Order> getOrderList(List<String> parsedOrders) {
        return parsedOrders.stream()
                .map(order -> {
                    validateOrder(order);
                    List<String> info = Arrays.asList(order.split("-"));
                    Menu menu = Menu.getMenu(info.get(0));
                    int quantity = validateQuantity(info.get(1));
                    return new Order(menu, quantity);
                }).toList();
    }

    private static void validateOrder(String input) {
        if (!input.contains("-")) {
            throw new ChristmasException(ErrorMessage.INVALID_ORDER);
        }
    }

    private static int validateQuantity(String input) {
        int quantity = parseQuantity(input);
        if (quantity < 1) {
            throw new ChristmasException(ErrorMessage.INVALID_ORDER);
        }
        return quantity;
    }

    private static int parseQuantity(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ChristmasException(ErrorMessage.INVALID_ORDER);
        }
    }

    private static void validateOrderList(List<Order> orderList) {
        if (isDuplicate(orderList)) {
            throw new ChristmasException(ErrorMessage.INVALID_ORDER);
        }
    }

    private static boolean isDuplicate(List<Order> orderList) {
        return orderList.stream()
                .map(Order::getMenu)
                .distinct()
                .count() != orderList.size();
    }
}
