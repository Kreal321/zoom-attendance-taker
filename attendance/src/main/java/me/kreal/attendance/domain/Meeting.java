package me.kreal.attendance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "meeting")
public class Meeting {
    // uuid
    @Id
    @Column(name = "meeting_uuid")
    private String meetingUuid;

    // id
    @Column(name = "meeting_id")
    private String meetingId;

    @Column(name = "host_id")
    private String hostId;

    @Column
    private String topic;

    @Column
    private Integer type;

    @Column(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    private Timestamp startTime;

    @Column(name = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="America/New_York")
    private Timestamp endTime;

    @OneToMany(orphanRemoval = true, mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<Attendance> attendances = new ArrayList<>();

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingUuid='" + meetingUuid + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", topic='" + topic + '\'' +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
