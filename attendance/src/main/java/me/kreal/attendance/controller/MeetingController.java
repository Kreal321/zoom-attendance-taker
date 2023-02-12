package me.kreal.attendance.controller;

import me.kreal.attendance.response.DataResponse;
import me.kreal.attendance.response.MeetingResponse;
import me.kreal.attendance.security.AuthUserDetails;
import me.kreal.attendance.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/all")
    public ResponseEntity<DataResponse> getUserMeetingList(@AuthenticationPrincipal AuthUserDetails userDetails) {

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .data(this.meetingService.getAllMeetingsByHostId(userDetails.getZoom_id()))
                        .build()
        );

    }

    @GetMapping("/{mId}")
    public ResponseEntity<DataResponse> getUserMeetingDetail(@AuthenticationPrincipal AuthUserDetails userDetails, @PathVariable Integer mId) {

        Optional<MeetingResponse> meetingResponseOptional = this.meetingService.getMeetingDetailByMIdAndHostId(mId, userDetails.getZoom_id());

        if (!meetingResponseOptional.isPresent()) {
            return ResponseEntity.ok(
                    DataResponse.builder()
                            .success(false)
                            .message("Meeting Id not found for current user.")
                            .build()
            );
        }

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .data(meetingResponseOptional.get())
                        .build()
        );

    }
}
