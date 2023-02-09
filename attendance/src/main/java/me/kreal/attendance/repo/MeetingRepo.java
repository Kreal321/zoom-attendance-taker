package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepo extends JpaRepository<Meeting, String> {
    List<Meeting> findAllByHostIdOrderByStartTimeDesc(String hostId);
    Optional<Meeting> findByMeetingUuidAndHostId(String meetingUuid, String hostId);

}
