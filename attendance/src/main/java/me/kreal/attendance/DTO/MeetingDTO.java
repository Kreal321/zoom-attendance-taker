package me.kreal.attendance.DTO;

import lombok.*;
import me.kreal.attendance.domain.Participant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MeetingDTO {
    private String id;
    private String uuid;
    private String host_id;
    private String topic;
    private Integer type;
    private String start_time;
    private String timezone;
    private Integer duration;
    private ParticipantDTO participant;
}
