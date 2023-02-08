package me.kreal.attendance.controller;

import me.kreal.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    private final NavTemplate nav;
    private final UserService userService;

    @Autowired
    public UserController(NavTemplate nav, UserService userService) {
        this.nav = nav;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(Model model, RedirectAttributes redirectedAttributes) {
        model.addAttribute("title", "Let's Quiz | Login");
        nav.setCurrentPage("login");
        nav.setNonShowPages("home", "meeting", "account");
        model.addAttribute("nav", nav.getUserNav());
        model.addAttribute("authorize_url", this.userService.getAuthorizeUrl());
        return new ModelAndView("user/login");
    }
}
