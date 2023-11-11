package christmas.model.event;

import java.util.Arrays;

public enum EventBadge {
    STAR("별", 5000, 9999),
    TREE("트리", 10000, 19999),
    SANTA("산타", 20000, Integer.MAX_VALUE),
    NONE("없음", 0, 0);
    private final String name;
    private final int min;
    private final int max;

    EventBadge(String name, int min, int max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public static EventBadge getBadge(int totalBenefit) {
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> eventBadge.isInRange(totalBenefit))
                .findAny()
                .orElse(NONE);
    }
    private boolean isInRange(int totalBenefit) {
        return min <= totalBenefit && totalBenefit <= max;
    }
    public String getName() {
        return name;
    }
}
