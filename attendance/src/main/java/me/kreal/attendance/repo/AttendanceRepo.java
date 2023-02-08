package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findAttendanceByMeetingUuidAndParticipantUuid(String meetingUuid, String participantUuid);
}
