package christmas.domain;

import java.util.List;

public enum Event {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인",
            List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25), false),
    WEEKDAY("평일 할인", List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31), false),
    WEEKEND("주말 할인", List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30), false),
    BONUS("증정 이벤트", List.of(), true);

    private static final Menu BONUS_MENU = Menu.CHAMPAGNE;
    private static final int BONUS_QUANTITY = 1;
    private static final int BONUS_CONDITION = 10000;

    private String name;
    private List<Integer> eventDays;
    private boolean bonus;

    Event(String name, List<Integer> eventDays, boolean bonus) {
        this.name = name;
        this.eventDays = eventDays;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getEventDays() {
        return eventDays;
    }

    public boolean isBonus() {
        return bonus;
    }

    public static Order getBonus(int totalPrice) {
        if (totalPrice >= BONUS_CONDITION) {
            return new Order(BONUS_MENU, BONUS_QUANTITY);
        }
        return null;
    }
}