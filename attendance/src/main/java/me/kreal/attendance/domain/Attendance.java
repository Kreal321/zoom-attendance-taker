package me.kreal.attendance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Integer attendanceId;

    @Column(name = "meeting_uuid")
    private String meetingUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "meeting_uuid", insertable = false, updatable = false)
    private Meeting meeting;

    @Column(name = "participant_uuid", insertable = false, updatable = false)
    private String participantUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_uuid")
    private Participant participant;

    @Column
    private Integer duration; // minutes

    @Column(name = "is_final")
    private Boolean isFinal; // minutes

    @OneToMany(orphanRemoval = true, mappedBy = "attendance", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", meetingUuid='" + meetingUuid + '\'' +
                ", participantUuid='" + participantUuid + '\'' +
                ", duration=" + duration +
                '}';
    }
}
