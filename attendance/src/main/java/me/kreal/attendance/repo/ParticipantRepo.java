package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Meeting;
import me.kreal.attendance.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, String> {

}
