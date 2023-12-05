package org.example.schedule;

import org.example.event.AbstractEvent;
import org.example.event.EventType;
import org.example.event.Meeting;
import org.example.event.Todo;
import org.example.reader.EventCsvReader;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private static final String NO_DISTURBANCE = "/data/no_disturbance.csv";
    private static final String MEETING = "/data/meeting.csv";
    private static final String OUT_OF_OFFICE = "/data/out_of_office.csv";
    private static final String TO_DO = "/data/to_do.csv";

    private List<AbstractEvent> events = new ArrayList<>();

    public Schedule() throws IOException {
        readDefaultData();
    }

    private void readDefaultData() throws IOException {
        EventCsvReader reader = new EventCsvReader();
        List<Meeting> meetings = reader.readMeeting(MEETING);
        meetings.forEach(this::add);

        List<Todo> todos = reader.readTodo(TO_DO);
        todos.forEach(this::add);
    }

    /**
     * 스케줄에 이벤트를 추가하려면 중복 되는 시간에 이벤트가 없는지 확인해야 한다.
     *
     * @param event
     */
    public void add(AbstractEvent event) {
        // 추가하려는 이벤트의 시작시간과 종료시간으로 겹치는 일정이 있는지 조회한다.
        if (hasScheduleConflictWith(event)) {
            System.out.println("겹치는 일정이 있어서 추가할 수 없습니다.");
            event.print();
            return;
        }

        // 없으면 추가한다.
        this.events.add(event);
    }

    public List<Todo> findTodos() {
        return events.stream()
                .filter(it -> it.support(EventType.TO_DO))
                .map(it -> (Todo) it)
                .collect(Collectors.toList());
    }

    public List<AbstractEvent> findEventsBy(ZonedDateTime baseAt) {
        ZonedDateTime midnight = baseAt.truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime nextDay = midnight.plusDays(1);
        return events.stream()
                .filter(it -> nextDay.isAfter(it.getStartAt()) && midnight.isBefore(it.getStartAt()))
                .sorted(orderByStartAtAsc())
                .collect(Collectors.toList());
    }

    private static Comparator<AbstractEvent> orderByStartAtAsc() {
        return Comparator.comparing(AbstractEvent::getStartAt);
    }

    public void printAll() {
        events.forEach(AbstractEvent::print);
    }

    public void remove(int id) {
        events = events.stream().filter(it -> it.getId() != id).collect(Collectors.toList());
    }

    private boolean hasScheduleConflictWith(AbstractEvent event) {
        return events.stream()
                .anyMatch(it ->
                        (event.getStartAt().isBefore(it.getEndAt()) && event.getStartAt().isAfter(it.getStartAt()))
                                || (event.getEndAt().isAfter(it.getStartAt())) && event.getEndAt().isBefore(it.getEndAt()));
    }
}
