package me.kreal.attendance.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ParticipantUpdateRequest {

    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String preferredName;

}
