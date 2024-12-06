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

    private List<Event> getAvailableEvents(Visit visit, Orders orders) {
        int visitDay = visit.getDay();

        return Arrays.stream(Event.values())
                .filter(event -> event.getEventDays().contains(visitDay))
                .toList();
    }
}
