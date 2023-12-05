package org.example.event.update;

import org.example.event.audit.AbstractAuditableEvent;

import java.time.ZonedDateTime;
import java.util.Set;

public class UpdateMeeting extends AbstractAuditableEvent {

    private final Set<String> participants;
    private final String meetingRoom;
    private final String agenda;

    public UpdateMeeting(String title, ZonedDateTime startAt, ZonedDateTime endAt, boolean deletedYn, Set<String> participants, String meetingRoom, String agenda) {
        super(title, startAt, endAt, deletedYn);
        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public String getAgenda() {
        return agenda;
    }
}
