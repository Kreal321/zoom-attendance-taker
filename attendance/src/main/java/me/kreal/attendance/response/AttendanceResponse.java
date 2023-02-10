package me.kreal.attendance.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import me.kreal.attendance.domain.Attendance;
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
    private Boolean isFinal;
    private ParticipantResponse participant;
    private Set<EventResponse> events = new HashSet<>();

    public AttendanceResponse(Attendance attendance) {
        this.attendanceId = attendance.getAttendanceId();
        this.meetingUuid = attendance.getMeetingUuid();
        this.participant = new ParticipantResponse(attendance.getParticipant());
        this.isFinal = attendance.getIsFinal();
        this.events = attendance.getEvents().stream()
                .map(EventResponse::new)
                .collect(Collectors.toSet());
        if (!this.isFinal) {
            long ts = attendance.getEvents().stream().map(e -> {
                if ("meeting.participant_joined".equals(e.getEventName())) {
                    return e.getEventTime().getTime() * -1;
                } else {
                    return e.getEventTime().getTime();
                }
            }).reduce(0L, Long::sum);
            this.duration = ts > 0 ? (int) ts / 60000 : -1;
        }
    }
}
