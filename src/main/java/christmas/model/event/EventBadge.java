package christmas.model.event;

public enum EventBadge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");
    private final String badge;

    EventBadge(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return badge;
    }
}
