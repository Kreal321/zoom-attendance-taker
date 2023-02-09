package me.kreal.attendance.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import me.kreal.attendance.domain.Participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantResponse {

    private String participantUuid;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String preferredName;

    public ParticipantResponse(Participant participant) {
        this.participantUuid = participant.getParticipantUuid();
        this.email = participant.getEmail();
        this.userName = participant.getUserName();
        this.firstName = participant.getFirstName();
        this.lastName = participant.getLastName();
        this.preferredName = participant.getPreferredName();
    }
}
