package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Discount {
    private List<Event> events;
    private Visit visit;
    private Orders orders;
    private Order bonus;

    public Discount(Visit visit, Orders orders) {
        this.visit = visit;
        this.orders = orders;
        this.events = getAvailableEvents();
        deleteEvent();
        bonus = Event.getBonus(orders.getTotalPrice().getPrice());
    }

    public List<Event> getEvents() {
        return events;
    }

    public Visit getVisit() {
        return visit;
    }

    public Orders getOrders() {
        return orders;
    }

    public Money getTotalDiscount() {
        int total = 0;

        if (events.contains(Event.CHRISTMAS_D_DAY)) total += Event.getChristmasDiscount(visit.getDay()).getPrice();
        if (events.contains(Event.WEEKDAY)) total += Event.getWeekdayDiscount(orders).getPrice();
        if (events.contains(Event.WEEKEND)) total += Event.getWeekendDiscount(orders).getPrice();
        if (events.contains(Event.SPECIAL)) total += Event.getSpecialDiscount(visit.getDay()).getPrice();
        if (bonus != null) total += bonus.getMenu().getMoney().getPrice() * bonus.getQuantity();

        return new Money(total);
    }

    public Money getAfterDiscount() {
        if (bonus != null) return new Money(orders.getTotalPrice().getPrice() - getTotalDiscount().getPrice() + bonus.getMenu().getMoney().getPrice());
        return new Money(orders.getTotalPrice().getPrice() - getTotalDiscount().getPrice());
    }

    private List<Event> getAvailableEvents() {
        int visitDay = visit.getDay();

        return Arrays.stream(Event.values())
                .filter(event -> event.getEventDays().contains(visitDay))
                .collect(Collectors.toList());
    }

    private void deleteEvent() {
        if (hasWeekday() && !hasDessert()) {
            events.remove(Event.WEEKDAY);
        }

        if (hasWeekend() && !hasMain()) {
            events.remove(Event.WEEKEND);
        }
    }

    private boolean hasWeekday() {
        return events.stream()
                .anyMatch(event -> event.getName().equals(Event.WEEKDAY.getName()));
    }

    private boolean hasWeekend() {
        return events.stream()
                .anyMatch(event -> event.getName().equals(Event.WEEKEND.getName()));
    }

    private boolean hasDessert() {
        return orders.getOrders().stream()
                .anyMatch(order -> order.getMenu().getMenuType() == MenuType.DESSERT);
    }

    private boolean hasMain() {
        return orders.getOrders().stream()
                .anyMatch(order -> order.getMenu().getMenuType() == MenuType.MAIN);
    }
}
