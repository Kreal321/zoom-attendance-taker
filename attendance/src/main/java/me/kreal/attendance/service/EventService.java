package me.kreal.attendance.service;

import lombok.Synchronized;
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
        assert event.getEId() == null;
        assert event.getAttendance() != null;
        return this.eventRepo.save(event);
    }

    public Optional<Event> findEventByAIdAndEventTime(Integer aId, long eventTime) {
        return this.eventRepo.findByAIdAndEventTime(aId, eventTime);
    }

    // Advance

    @Synchronized
    public Event findOrCreateEventFromAttendance(Attendance attendance, long eventTime, String eventName) {
        Optional<Event> eventOptional = this.findEventByAIdAndEventTime(attendance.getAId(), eventTime);

        if (eventOptional.isPresent()) return eventOptional.get();

        Event event = Event.builder()
                .attendance(attendance)
                .eventTime(eventTime)
                .eventName(eventName)
                .build();
        return this.saveEvent(event);
    }

}
