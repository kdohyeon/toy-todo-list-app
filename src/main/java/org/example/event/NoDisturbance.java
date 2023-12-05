package org.example.event;

import org.example.event.audit.AbstractAuditableEvent;

import java.time.ZonedDateTime;

import static org.example.util.ZonedDateTimeUtils.of;

public class NoDisturbance extends AbstractEvent {

    public NoDisturbance(int id, ZonedDateTime startAt, ZonedDateTime endAt) {
        super(id, "방해 금지 모드", startAt, endAt);
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.NO_DISTURBANCE;
    }

    @Override
    public void print() {
        System.out.printf("[방해금지]%n");
        System.out.printf(" - 시간 : %s ~ %s%n", of(getStartAt()), of(getEndAt()));
    }

    @Override
    protected void update(AbstractAuditableEvent event) {

    }
}
