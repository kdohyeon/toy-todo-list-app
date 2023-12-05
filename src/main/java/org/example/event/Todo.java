package org.example.event;

import org.example.event.audit.AbstractAuditableEvent;
import org.example.event.update.UpdateTodo;

import java.time.ZonedDateTime;

import static org.example.util.ZonedDateTimeUtils.of;

public class Todo extends AbstractEvent {

    private String description;

    public Todo(int id, String title, String description, ZonedDateTime startAt, ZonedDateTime endAt) {
        super(id, title, startAt, endAt);

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.TO_DO;
    }

    @Override
    public void print() {
        System.out.printf("[할 일] %s : %s%n", getTitle(), description);
        System.out.printf(" - 시간 : %s ~ %s%n", of(getStartAt()), of(getEndAt()));
    }

    @Override
    protected void update(AbstractAuditableEvent event) {
        UpdateTodo todo = (UpdateTodo) event;
        this.description = todo.getDescription();
    }
}
