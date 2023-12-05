package org.example.event;

import org.example.event.audit.AbstractAuditableEvent;
import org.example.exception.InvalidEventException;
import org.example.exception.InvalidScheduleException;

import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class AbstractEvent implements Event {
    private final int id;
    private String title;

    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private Duration duration;

    private final ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;

    private boolean deletedYn;

    protected AbstractEvent(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new InvalidScheduleException(String.format("시작일은 종료일보다 이전이어야 합니다. 시작일=%s, 종료일=%s", startAt, endAt));
        }

        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = Duration.between(startAt, endAt);

        ZonedDateTime now = ZonedDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;

        deletedYn = false;
    }

    public void delete() {
        deletedYn = true;
    }

    public int getId() {
        return id;
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

    public boolean getDeletedYn() {
        return deletedYn;
    }

    public boolean isBetween(ZonedDateTime startAt, ZonedDateTime endAt) {
        return this.startAt.isAfter(startAt) && this.endAt.isBefore(endAt);
    }


    public boolean removeTarget(int id) {
        return this.id == id;
    }

    public void validateAndUpdate(AbstractAuditableEvent event) {
        if (getDeletedYn()) {
            print();
            throw new InvalidEventException("이미 삭제된 이벤트는 수정할 수 없습니다.");
        }

        update(event);
        defaultUpdate(event);
    }

    protected abstract void update(AbstractAuditableEvent event);

    private void defaultUpdate(AbstractAuditableEvent event) {
        AbstractAuditableEvent ae = event;
        this.title = ae.getTitle();
        this.startAt = ae.getStartAt();
        this.endAt = ae.getEndAt();
        this.duration = Duration.between(ae.getStartAt(), ae.getEndAt());
        this.modifiedAt = ZonedDateTime.now();
    }
}
