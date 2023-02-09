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
    private String participant_uuid; // only valid for the duration of the meeting
    private String email;
    private String registrant_id;
    private String participant_user_id; // participant's universally unique ID (UUID):
    private String customer_key;

}
