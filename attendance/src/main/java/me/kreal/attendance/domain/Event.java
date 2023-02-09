package me.kreal.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "attendance_id", insertable = false, updatable = false)
    private Integer attendanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    @Column(name = "event_time")
    private Timestamp eventTime;

    @Column(name = "event_name")
    private String eventName;

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", attendanceId=" + attendanceId +
                ", eventTime=" + eventTime +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}
