package me.kreal.attendance.controller;

import me.kreal.attendance.DTO.MeetingDTO;
import me.kreal.attendance.domain.Meeting;
import me.kreal.attendance.request.ZoomRequest;
import me.kreal.attendance.response.DataResponse;
import me.kreal.attendance.response.TokenResponse;
import me.kreal.attendance.response.ValidationResponse;
import me.kreal.attendance.service.MeetingService;
import me.kreal.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("/zoom")
public class ZoomController {

    private final MeetingService meetingService;
    private final UserService userService;

    @Autowired
    public ZoomController(MeetingService meetingService, UserService userService) {
        this.meetingService = meetingService;
        this.userService = userService;
    }


    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<DataResponse> test() {

        return ResponseEntity.ok(DataResponse.builder().success(true).message("Connection succeeded.").build());

    }


    @PostMapping("/post/new")
    @ResponseBody
    public ResponseEntity<ValidationResponse> zoomPostRequest(@RequestBody ZoomRequest request) {

        System.out.println(request);

        if (request.getPayload().getObject() != null) this.meetingService.handleZoomRequest(request);

        return ResponseEntity.ok(
                ValidationResponse.builder()
                        .plainToken(request.getPayload().getPlainToken())
                        .encryptedToken(this.meetingService.getEncryptedToken(request.getPayload().getPlainToken()))
                        .build()
        );

    }

    @GetMapping("/oauth2/login")
    @ResponseBody
    public ResponseEntity<DataResponse> getZoomLoginLink() {
        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .data(this.userService.getAuthorizeUrl())
                        .build()
        );
    }

    @GetMapping("/oauth2/token")
    @ResponseBody
    public ResponseEntity<DataResponse> setJwtTokenCookie(@RequestParam String code, HttpServletResponse response) {

        Optional<String> tokenOptional = this.userService.issueToken(code);

        if (!tokenOptional.isPresent()) {
            return ResponseEntity.ok(
                    DataResponse.builder()
                            .success(false)
                            .message("Code is invalided.")
                            .build()
            );
        }

        Cookie cookie = new Cookie("token", tokenOptional.get());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .message("Token has been issued.")
                        .data(tokenOptional.get())
                        .build()
        );

    }

}
