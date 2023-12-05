package org.example.event;

import org.example.event.audit.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class OutOfOffice extends AbstractEvent {
    protected OutOfOffice(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt) {
        super(id, title, startAt, endAt);
    }

    @Override
    protected void update(AbstractAuditableEvent event) {

    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.OUT_OF_OFFICE;
    }

    @Override
    public void print() {
        System.out.printf("[부재중]%n");
        System.out.printf(" - 시간 : %s ~ %s%n", getStartAt(), getEndAt());
    }
}
