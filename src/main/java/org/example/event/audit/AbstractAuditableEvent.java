package org.example.event.audit;

import java.time.ZonedDateTime;

public abstract class AbstractAuditableEvent {
    private final String title;

    private final ZonedDateTime startAt;
    private final ZonedDateTime endAt;

    private final boolean deletedYn;

    protected AbstractAuditableEvent(String title, ZonedDateTime startAt, ZonedDateTime endAt, boolean deletedYn) {
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.deletedYn = deletedYn;
    }

    public String getTitle() {
        return title;
    }

    public ZonedDateTime getStartAt() {
        return startAt;
    }

    public ZonedDateTime getEndAt() {
        return endAt;
    }

    public boolean isDeletedYn() {
        return deletedYn;
    }
}
