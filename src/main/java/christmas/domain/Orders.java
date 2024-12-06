package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<Order> orders;
    private Money totalPrice;

    public Orders(List<Order> orders) {
        this.orders = orders;
        this.totalPrice = calculateTotalPrice();
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    private Money calculateTotalPrice() {
        int total = orders.stream()
                .mapToInt(order -> {
                    Menu menu = order.getMenu();
                    return menu.getMoney().getPrice() * order.getQuantity();
                })
                .sum();

        return new Money(total);
    }
}
