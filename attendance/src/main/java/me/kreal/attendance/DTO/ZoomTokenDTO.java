package me.kreal.attendance.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ZoomTokenDTO {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;

}
