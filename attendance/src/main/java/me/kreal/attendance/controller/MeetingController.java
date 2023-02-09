package me.kreal.attendance.controller;

import me.kreal.attendance.request.MeetingUuidRequest;
import me.kreal.attendance.response.DataResponse;
import me.kreal.attendance.response.MeetingResponse;
import me.kreal.attendance.security.AuthUserDetails;
import me.kreal.attendance.service.MeetingService;
import me.kreal.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/detail")
    public ResponseEntity<DataResponse> getUserMeetingDetail(@AuthenticationPrincipal AuthUserDetails userDetails, @RequestBody MeetingUuidRequest request) {

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .data(this.meetingService.getMeetingDetailByMeetingUuidAndHostId(request.getMeetingUuid(), userDetails.getZoom_id()))
                        .build()
        );

    }
}
