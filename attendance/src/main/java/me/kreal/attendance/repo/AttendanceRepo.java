package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    @Query("select a from Attendance a where a.mId = ?1 and a.pId = ?2")
    Optional<Attendance> findAttendanceByMIdAndPId(Integer mId, Integer pId);
}
