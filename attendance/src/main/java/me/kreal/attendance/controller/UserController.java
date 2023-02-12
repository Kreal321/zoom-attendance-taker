package me.kreal.attendance.controller;

import me.kreal.attendance.response.DataResponse;
import me.kreal.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public ResponseEntity<DataResponse> getLogin() {

        return ResponseEntity.ok(
                DataResponse.builder()
                        .success(true)
                        .message("Token is validated.")
                        .build()
        );
    }
}
