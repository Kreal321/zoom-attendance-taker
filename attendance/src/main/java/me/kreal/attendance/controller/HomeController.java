package me.kreal.attendance.controller;

import me.kreal.attendance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final NavTemplate nav;
    private final UserService userService;

    @Autowired
    public HomeController(NavTemplate nav, UserService userService) {
        this.nav = nav;
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView getHome(Model model, RedirectAttributes redirectedAttributes) {
        model.addAttribute("title", "Zoom Attendance Taker | Home");
        nav.setCurrentPage("home");
        nav.setNonShowPages("login");
        model.addAttribute("nav", nav.getUserNav());
        return new ModelAndView("home/index");
    }
}
