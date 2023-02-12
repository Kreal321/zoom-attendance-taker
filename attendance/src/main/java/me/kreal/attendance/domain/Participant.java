package me.kreal.attendance.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "participant_uuid")
    private String participantUuid;

    @Column
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Override
    public String toString() {
        return "Participant{" +
                "pId='" + pId + '\'' +
                ", participantUuid='" + participantUuid + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
