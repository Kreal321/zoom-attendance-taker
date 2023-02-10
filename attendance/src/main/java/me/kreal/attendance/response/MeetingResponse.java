package me.kreal.attendance.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Meeting;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingResponse {
    private String meetingUuid;
    private String meetingId;
    private String hostId;
    private String topic;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Timestamp startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private Timestamp endTime;
    private List<AttendanceResponse> attendances = new ArrayList<>();

    public MeetingResponse(Meeting meeting) {
        this.meetingUuid = meeting.getMeetingUuid();
        this.meetingId = meeting.getMeetingId();
        this.hostId = meeting.getHostId();
        this.topic = meeting.getTopic();
        this.type = meeting.getType();
        this.startTime = meeting.getStartTime();
        this.endTime = meeting.getEndTime();
        List<AttendanceResponse> temp = meeting.getAttendances().stream()
                .map(AttendanceResponse::new)
                .collect(Collectors.toList());
        this.attendances = meeting.getAttendances().stream()
                                .map(AttendanceResponse::new)
                                .collect(Collectors.toList());
    }
}
