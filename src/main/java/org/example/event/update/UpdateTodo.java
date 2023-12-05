package org.example.event.update;

import org.example.event.audit.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class UpdateTodo extends AbstractAuditableEvent {
    private final String description;

    public UpdateTodo(String description, String title, ZonedDateTime startAt, ZonedDateTime endAt, boolean deletedYn) {
        super(title, startAt, endAt, deletedYn);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
