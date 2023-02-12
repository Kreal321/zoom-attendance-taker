package me.kreal.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    @Column(name = "e_id")
    private Integer eId;

    @Column(name = "a_id", insertable = false, updatable = false)
    private Integer aId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "a_id")
    private Attendance attendance;

    @Column(name = "event_time")
    private Long eventTime;

    @Column(name = "event_name")
    private String eventName;

    @Override
    public String toString() {
        return "Event{" +
                "eId=" + eId +
                ", attendanceId=" + aId +
                ", eventTime=" + eventTime +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}
