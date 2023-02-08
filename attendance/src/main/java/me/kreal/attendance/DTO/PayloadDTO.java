package me.kreal.attendance.DTO;

import lombok.*;
import me.kreal.attendance.domain.Meeting;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PayloadDTO {
    private String plainToken;
    private MeetingDTO object;
}
