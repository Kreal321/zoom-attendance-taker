package me.kreal.attendance.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MeetingUuidRequest {
    private String meetingUuid;
}
