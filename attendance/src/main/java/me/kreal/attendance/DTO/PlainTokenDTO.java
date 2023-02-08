package me.kreal.attendance.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PlainTokenDTO {
    private String plainToken;
}
