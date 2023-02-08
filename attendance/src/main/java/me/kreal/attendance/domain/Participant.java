package me.kreal.attendance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import me.kreal.attendance.DTO.ParticipantDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @Column(name = "participant_uuid")
    private String participantUuid;

    @Column
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "preferred_name")
    private String preferredName;

}
