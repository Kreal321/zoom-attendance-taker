package me.kreal.attendance.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Event;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse {

    private Integer eventId;
    private Integer attendanceId;
    private Timestamp eventTime;
    private String eventName;

    public EventResponse(Event event) {
        this.eventId = event.getEventId();
        this.attendanceId = event.getAttendanceId();
        this.eventTime = event.getEventTime();
        this.eventName = event.getEventName();
    }
}
