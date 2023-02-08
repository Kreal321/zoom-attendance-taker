package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    Optional<Event> findEventByAttendanceIdAndEventTime(Integer attendanceId, Timestamp eventTime);
}
