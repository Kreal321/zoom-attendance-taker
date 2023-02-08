package me.kreal.attendance.service;

import me.kreal.attendance.DTO.MeetingDTO;
import me.kreal.attendance.DTO.ParticipantDTO;
import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Event;
import me.kreal.attendance.domain.Meeting;
import me.kreal.attendance.domain.Participant;
import me.kreal.attendance.repo.MeetingRepo;
import me.kreal.attendance.request.ZoomRequest;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class MeetingService {

    private final MeetingRepo meetingRepo;
    private final ParticipantService participantService;
    private final AttendanceService attendanceService;
    private final EventService eventService;

    @Autowired
    public MeetingService(MeetingRepo meetingRepo, ParticipantService participantService, AttendanceService attendanceService, EventService eventService) {
        this.meetingRepo = meetingRepo;
        this.participantService = participantService;
        this.attendanceService = attendanceService;
        this.eventService = eventService;
    }

    // Basic
    public Meeting saveMeeting(Meeting meeting) {
        assert meeting.getMeetingUuid() != null;
        return this.meetingRepo.save(meeting);
    }

    public Optional<Meeting> findMeetingByUuid(String meetingUuid) {
        return this.meetingRepo.findById(meetingUuid);
    }

    // Advance
    public Meeting findOrCreateFrom(MeetingDTO meetingDTO) {
        Optional<Meeting> meetingOptional = this.findMeetingByUuid(meetingDTO.getUuid());

        if (meetingOptional.isPresent()) return meetingOptional.get();

        Timestamp event_time = new Timestamp(new Date().getTime());
        try {
            //        start_time=2023-02-03T21:28:47Z, timezone=America/New_York
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = isoFormat.parse(meetingDTO.getStart_time());
            event_time = new Timestamp(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Meeting m = Meeting.builder()
                .meetingUuid(meetingDTO.getUuid())
                .meetingId(meetingDTO.getId())
                .hostId(meetingDTO.getHost_id())
                .topic(meetingDTO.getTopic())
                .type(meetingDTO.getType())
                .startTime(event_time)
                .build();

        return this.saveMeeting(m);
    }



    public void handleZoomRequest(ZoomRequest request) {

        Timestamp eventTime = new Timestamp(request.getEvent_ts());
        MeetingDTO meetingDTO = request.getPayload().getObject();

        // fix participant uuid null
        if (meetingDTO.getParticipant().getParticipant_uuid() == null) meetingDTO.getParticipant().setParticipant_uuid(meetingDTO.getHost_id());

        Meeting m = this.findOrCreateFrom(meetingDTO);

        switch (request.getEvent()) {
            case "meeting.started":
                break;
            case "meeting.ended":
                m.setEndTime(eventTime);
                this.saveMeeting(m);
                break;
            case "meeting.participant_joined":
            case "meeting.participant_left":
                Participant p = this.participantService.findOrCreateFrom(meetingDTO.getParticipant());
                Attendance a = this.attendanceService.findOrCreateAttendanceByMeetingAndParticipant(m, p);
                Event e = this.eventService.findOrCreateEventFromAttendance(a, eventTime, request.getEvent());

                if (a.getEvents().add(e)) this.attendanceService.saveAttendance(a);

                break;
            case "endpoint.url_validation":
                break;
            default:
                System.out.println(request.getEvent());
                break;
        }

    }

    private String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Hex.toHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }

    public String getEncryptedToken(String plainToken) {

        String key = "BDoBwv1yROmeqXiroQdirA";

        if (plainToken == null) return "";

        try {
            return encode(key, plainToken);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "";

    }
}
