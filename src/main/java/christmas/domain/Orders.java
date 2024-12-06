package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public Money getTotalPriceBeforeDiscount() {
        int total = orders.stream()
                .mapToInt(order -> {
                    Menu menu = order.getMenu();
                    return menu.getMoney().getPrice() * order.getQuantity();
                })
                .sum();

        return new Money(total);
    }
}
