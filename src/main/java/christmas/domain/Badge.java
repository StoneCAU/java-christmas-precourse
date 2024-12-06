package christmas.domain;

public enum Badge {
    STAR("별", new Money(5000)),
    TREE("트리", new Money(10000)),
    SANTA("산타", new Money(20000)),
    NULL("없음", null);

    private String name;
    private Money condition;

    Badge(String name, Money condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public Money getCondition() {
        return condition;
    }

    public static Badge getBadge(int discount) {
        if (discount >= SANTA.getCondition().getPrice()) return SANTA;
        if (discount >= TREE.getCondition().getPrice()) return TREE;
        if (discount >= STAR.getCondition().getPrice()) return STAR;
        return NULL;
    }
}
