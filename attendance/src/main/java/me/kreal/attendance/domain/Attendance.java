package me.kreal.attendance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Integer aId;

    @Column(name = "m_id")
    private Integer mId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "m_id", insertable = false, updatable = false)
    private Meeting meeting;

    @Column(name = "p_id", insertable = false, updatable = false)
    private Integer pId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    private Participant participant;

    @Column
    private Integer duration; // minutes

    @Column(name = "is_final")
    private Boolean isFinal; // minutes

    @OneToMany(orphanRemoval = true, mappedBy = "attendance", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    @Override
    public String toString() {
        return "Attendance{" +
                "aId=" + aId +
                ", mId=" + mId +
                ", pId=" + pId +
                ", duration=" + duration +
                ", isFinal=" + isFinal +
                '}';
    }
}
