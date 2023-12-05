package org.example.event;

import org.example.event.audit.AbstractAuditableEvent;
import org.example.event.update.UpdateMeeting;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.example.util.ZonedDateTimeUtils.*;

public class Meeting extends AbstractEvent {

    private Set<String> participants;
    private String meetingRoom;
    private String agenda;

    public Meeting(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt, Set<String> participants, String meetingRoom, String agenda) {
        super(id, title, startAt, endAt);
        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.MEETING;
    }

    @Override
    protected void update(AbstractAuditableEvent event) {
        UpdateMeeting meeting = (UpdateMeeting) event;
        this.participants = meeting.getParticipants();
        this.meetingRoom = meeting.getMeetingRoom();
        this.agenda = meeting.getAgenda();
    }

    @Override
    public void print() {
        System.out.printf("[회의] %s : %s%n", getTitle(), agenda);
        System.out.printf(" - 시간 : %s ~ %s%n", of(getStartAt()), of(getEndAt()));
        System.out.printf(" - 회의실 : %s%n", meetingRoom);
        System.out.printf(" - 참석자 : %s%n", String.join(",", participants.stream().toList()));
    }

    public Set<String> getParticipants() {
        return this.participants;
    }

    public String getMeetingRoom() {
        return this.meetingRoom;
    }

    public String getAgenda() {
        return this.agenda;
    }
}
