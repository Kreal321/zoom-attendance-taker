package me.kreal.attendance.request;

import lombok.*;
import me.kreal.attendance.DTO.PayloadDTO;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ZoomRequest {
    private PayloadDTO payload;
    private long event_ts;
    private String event;
}
