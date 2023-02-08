package me.kreal.attendance.DTO;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ParticipantDTO {
    private String user_id;
    private String user_name;
    private String id;
    private String participant_uuid;
    private String email;
    private String registrant_id;
    private String participant_user_id;
    private String customer_key;

}
