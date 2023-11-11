package christmas.model.event;

public enum EventName {
    CHRISTMAS("크리스마스 디데이 할인"),
    SPECIAL("특별 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    GIVEAWAY("증정 이벤트");

    private final String eventName;

    EventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
