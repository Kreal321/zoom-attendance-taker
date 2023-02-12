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

    private Integer eId;
    private Integer aId;
    private Long eventTime;
    private String eventName;

    public EventResponse(Event event) {
        this.eId = event.getEId();
        this.aId = event.getAId();
        this.eventTime = event.getEventTime();
        this.eventName = event.getEventName();
    }
}
