package me.kreal.attendance.service;

import me.kreal.attendance.domain.Attendance;
import me.kreal.attendance.domain.Event;
import me.kreal.attendance.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepo eventRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    // Basic
    public Event saveEvent(Event event) {
        assert event.getEventId() == null;
        assert event.getAttendance() != null;
        return this.eventRepo.save(event);
    }

    public Optional<Event> findEventByAttendanceAndEventTime(Attendance attendance, Timestamp eventTime) {
        return this.eventRepo.findEventByAttendanceIdAndEventTime(attendance.getAttendanceId(), eventTime);
    }

    // Advance
    public Event findOrCreateEventFromAttendance(Attendance attendance, Timestamp eventTime, String eventName) {
        Optional<Event> eventOptional = this.findEventByAttendanceAndEventTime(attendance, eventTime);

        if (eventOptional.isPresent()) return eventOptional.get();

        Event event = Event.builder()
                .attendance(attendance)
                .eventTime(eventTime)
                .eventName(eventName)
                .build();
        return this.saveEvent(event);
    }

}
