package me.kreal.attendance.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Event;
import me.kreal.attendance.domain.Meeting;
import me.kreal.attendance.domain.Participant;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttendanceResponse {

    private Integer attendanceId;
    private String meetingUuid;
    private Integer duration; // minutes
    private ParticipantResponse participant;
    private Set<EventResponse> events = new HashSet<>();

    public AttendanceResponse(Attendance attendance) {
        this.attendanceId = attendance.getAttendanceId();
        this.meetingUuid = attendance.getMeetingUuid();
        this.participant = new ParticipantResponse(attendance.getParticipant());
        this.events = attendance.getEvents().stream()
                        .map(EventResponse::new)
                        .collect(Collectors.toSet());
    }
}
