package christmas.domain;

import java.util.Arrays;
import java.util.List;

public class Discount {
    private List<Event> events;
    private Visit visit;
    private Orders orders;

    public Discount(Visit visit, Orders orders) {
        this.visit = visit;
        this.orders = orders;
        this.events = getAvailableEvents(visit, orders);
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
        Order bonus = Event.getBonus(orders.getTotalPrice().getPrice());

        if (events.contains(Event.CHRISTMAS_D_DAY)) total += Event.getChristmasDiscount(visit.getDay()).getPrice();
        if (events.contains(Event.WEEKDAY)) total += Event.getWeekdayDiscount(orders).getPrice();
        if (events.contains(Event.WEEKEND)) total += Event.getWeekendDiscount(orders).getPrice();
        if (events.contains(Event.SPECIAL)) total += Event.getSpecialDiscount(visit.getDay()).getPrice();
        if (bonus != null) total += bonus.getMenu().getMoney().getPrice() * bonus.getQuantity();

        return new Money(total);
    }

    public Money getAfterDiscount() {
        return new Money(orders.getTotalPrice().getPrice() - getTotalDiscount().getPrice());
    }

    private List<Event> getAvailableEvents(Visit visit, Orders orders) {
        int visitDay = visit.getDay();

        return Arrays.stream(Event.values())
                .filter(event -> event.getEventDays().contains(visitDay))
                .toList();
    }
}
