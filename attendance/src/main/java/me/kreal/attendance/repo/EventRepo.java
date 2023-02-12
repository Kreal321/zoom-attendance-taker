package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    @Query("select e from Event e where e.aId = ?1 and e.eventTime = ?2")
    Optional<Event> findByAIdAndEventTime(Integer aId, long eventTime);
}
