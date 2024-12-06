package christmas.domain;

import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.Arrays;

public enum Menu {
    // 에피타이저
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", new Money(6000)),
    TAPAS(MenuType.APPETIZER, "타파스", new Money(5500)),
    SALAD(MenuType.APPETIZER, "시저샐러드", new Money(8000)),

    // 메인
    STEAK(MenuType.MAIN, "티본스테이크", new Money(55000)),
    BBQ_RIP(MenuType.MAIN, "바비큐립", new Money(54000)),
    SEA_FOOD_PASTA(MenuType.MAIN, "해산물파스타", new Money(35000)),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", new Money(25000)),

    // 디저트
    CAKE(MenuType.DESSERT, "초코케이크", new Money(15000)),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", new Money(5000)),

    // 음료
    COLA(MenuType.DRINK, "제로콜라", new Money(3000)),
    WINE(MenuType.DRINK, "레드와인", new Money(60000)),
    CHAMPAGNE(MenuType.DRINK, "샴페인", new Money(25000));

    private final MenuType menuType;
    private final String name;
    private final Money money;

    Menu(MenuType menuType, String name, Money money) {
        this.menuType = menuType;
        this.name = name;
        this.money = money;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }

    public static Menu getMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new ChristmasException(ErrorMessage.INVALID_ORDER));
    }

    @Override
    public String toString() {
        return getName() + "(" + getMoney().toString() + ")";
    }
}
