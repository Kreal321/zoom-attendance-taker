package me.kreal.attendance.controller;

import me.kreal.attendance.response.DataResponse;
import me.kreal.attendance.security.AuthUserDetails;
import me.kreal.attendance.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
//        System.out.println(userDetails.getZoom_id());

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .data(this.meetingService.getAllMeetingsByHostId(userDetails.getZoom_id()))
                        .build()
        );

    }

    @GetMapping("/{mId}")
    public ResponseEntity<DataResponse> getUserMeetingDetail(@AuthenticationPrincipal AuthUserDetails userDetails, @PathVariable Integer mId) {

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .data(this.meetingService.getMeetingDetailByMIdAndHostId(mId, userDetails.getZoom_id()))
                        .build()
        );

    }
}
