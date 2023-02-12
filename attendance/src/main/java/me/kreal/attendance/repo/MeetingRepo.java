package me.kreal.attendance.repo;

import me.kreal.attendance.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepo extends JpaRepository<Meeting, Integer> {

    Optional<Meeting> findByMeetingUuid(String meetingUuid);
    List<Meeting> findAllByHostIdAndIsDeletedOrderByStartTimeDesc(String hostId, Boolean isDeleted);

    @Query("select m from Meeting m where m.mId = ?1 and m.hostId = ?2 and m.isDeleted = false")
    Optional<Meeting> findByMIdAndHostIdNotDeleted(Integer mId, String hostId);


}
