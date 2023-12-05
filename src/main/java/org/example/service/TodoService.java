package org.example.service;

import org.example.event.Todo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoService {

    private List<Todo> events = new ArrayList<>();

    public void create(Todo event) {
        this.events.add(event);
    }

    public List<Todo> findAll() {
        return events;
    }

    public List<Todo> findBetween(final ZonedDateTime startAt, final ZonedDateTime endAt) {
        return events.stream()
                .filter(event -> event.isBetween(startAt, endAt))
                .collect(Collectors.toList());
    }

    public void update(Todo todo) {

    }

    public void delete() {

    }

    public void print(List<Todo> events) {
        events.forEach(each -> {
            System.out.printf("[%s ~ %s] %s%n", each.getStartAt(), each.getEndAt(), each.getTitle());
            System.out.printf("- 설명: %s%n", each.getDescription());
            System.out.println();
        });
    }
}

