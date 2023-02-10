package me.kreal.attendance.service;

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
        assert attendance.getAttendanceId() == null;
        assert attendance.getMeetingUuid() != null;
        assert attendance.getParticipant() != null && attendance.getParticipant().getParticipantUuid() != null;

        return this.attendanceRepo.save(attendance);
    }

    public Optional<Attendance> findMeetingById(Integer attendanceId) {
        return this.attendanceRepo.findById(attendanceId);
    }

    public Optional<Attendance> findMeetingByMeetingUuidAndParticipantUuid(String meetingUuid, String participantUuid) {
        return this.attendanceRepo.findAttendanceByMeetingUuidAndParticipantUuid(meetingUuid, participantUuid);
    }

    // Advance

    public Attendance findOrCreateAttendanceByMeetingAndParticipant(Meeting meeting, Participant participant) {
        assert participant != null;

        Optional<Attendance> attendanceOptional = this.findMeetingByMeetingUuidAndParticipantUuid(meeting.getMeetingUuid(), participant.getParticipantUuid());

        if (attendanceOptional.isPresent()) return attendanceOptional.get();

        Attendance attendance = Attendance.builder()
                .meetingUuid(meeting.getMeetingUuid())
                .participant(participant)
                .duration(0)
                .events(new HashSet<>())
                .isFinal(false)
                .build();

        return this.saveAttendance(attendance);

    }
}
