package me.kreal.attendance.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private Integer mId;

    // uuid
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
    private Long startTime;

    @Column(name = "end_time")
    private Long endTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(orphanRemoval = true, mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<Attendance> attendances = new ArrayList<>();

    @Override
    public String toString() {
        return "Meeting{" +
                "mId=" + mId +
                ", meetingUuid='" + meetingUuid + '\'' +
                ", meetingId='" + meetingId + '\'' +
                ", hostId='" + hostId + '\'' +
                ", topic='" + topic + '\'' +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
