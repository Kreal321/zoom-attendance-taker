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

    private Integer pId;
    private String participantUuid;
    private String email;
    private String userName;

    public ParticipantResponse(Participant participant) {
        this.pId = participant.getPId();
        this.participantUuid = participant.getParticipantUuid();
        this.email = participant.getEmail();
        this.userName = participant.getUserName();
    }
}
