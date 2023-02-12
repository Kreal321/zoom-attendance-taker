package me.kreal.attendance.service;

import lombok.Synchronized;
import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Meeting;
import me.kreal.attendance.domain.Participant;
import me.kreal.attendance.repo.AttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepo attendanceRepo;

    @Autowired
    public AttendanceService(AttendanceRepo attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }


    // Basic
    public Attendance saveAttendance(Attendance attendance) {
        assert attendance.getAId() == null;
        assert attendance.getMId() != null;
        assert attendance.getParticipant() != null && attendance.getParticipant().getParticipantUuid() != null;

        return this.attendanceRepo.save(attendance);
    }

    public Optional<Attendance> findAttendanceById(Integer aId) {
        assert aId != null;
        return this.attendanceRepo.findById(aId);
    }

    public Optional<Attendance> findAttendanceByMIdAndPId(Integer mId, Integer pId) {
        return this.attendanceRepo.findAttendanceByMIdAndPId(mId, pId);
    }

    // Advance

    @Synchronized
    public Attendance findOrCreateAttendanceByMeetingAndParticipant(Meeting meeting, Participant participant) {
        assert participant != null;

        Optional<Attendance> attendanceOptional = this.findAttendanceByMIdAndPId(meeting.getMId(), participant.getPId());

        if (attendanceOptional.isPresent()) return attendanceOptional.get();

        Attendance attendance = Attendance.builder()
                .mId(meeting.getMId())
                .participant(participant)
                .duration(0)
                .events(new HashSet<>())
                .isFinal(false)
                .build();

        return this.saveAttendance(attendance);

    }
}
