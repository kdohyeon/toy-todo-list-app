package org.example.event;

public enum EventType {
    MEETING("회의"),
    NO_DISTURBANCE("방해 금지 시간"),
    TO_DO("할 일"),
    OUT_OF_OFFICE("부재 중");

    private final String description;

    EventType(String description) {
        this.description = description;
    }
}
